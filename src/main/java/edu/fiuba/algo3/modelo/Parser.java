package edu.fiuba.algo3.modelo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.InputStream;
import java.util.*;

public class Parser {

    private final JsonNode rootNode; // Nodo raíz del JSON

    public Parser(String fileName) throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        // Obtener el archivo desde el classpath
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName);
        if (inputStream == null) {
            throw new IllegalArgumentException("Archivo JSON no encontrado: " + fileName);
        }

        // Cargar JSON como árbol
        rootNode = mapper.readTree(inputStream);
    }

    // Metodo para obtener las rondas como objetos Round
    public Rounds parseRounds() {
        List<Round> rounds = new ArrayList<>();
        JsonNode roundsNode = rootNode.get("rondas");

        for (JsonNode roundNode : roundsNode) {
            int hands = roundNode.get("manos").asInt();
            int discards = roundNode.get("descartes").asInt();
            int targetScore = roundNode.get("puntajeASuperar").asInt();

            Round round = new Round(hands, discards, targetScore);
            rounds.add(round);
        }

        return (new Rounds(rounds));
    }

    //Metodo para obtener el mazo como una lista de Cards
    public Deck_I parseDeck() {
        List<Card> cards = new ArrayList<>();
        JsonNode deckNode = rootNode.get("mazo");

        for (JsonNode cardNode : deckNode) {
            cards.add(this.parseCard(cardNode));
        }

        return new Deck(cards);
    }

    public List<Store> parseStores(Player player, Rounds rounds) {
        List<Store> stores = new ArrayList<>();
        JsonNode roundsNode = rootNode.get("rondas");

        for (int i = 0; i < roundsNode.size(); i++) {
            stores.add(this.parseStore(i, player, rounds));
        }

        return stores;
    }

    public Store parseStore(int index, Player player, Rounds rounds) {
        List<TarotApply> tarots = this.parseTarot(index, player);
        List<JokerApply> jokers = this.parseJoker(index, rounds, player);
        Card cards = this.parseStoreCard(index);
        return new Store(jokers, tarots, List.of(cards));
    }

    // Metodo para obtener los Tarots de una ronda
    public List<TarotApply> parseTarot(int roundIndex, Player player) {
        List<TarotApply> tarots = new ArrayList<>();
        JsonNode tarotsNode = rootNode.get("rondas").get(roundIndex).get("tienda").get("tarots");

        for (JsonNode tarotNode : tarotsNode) {
            String name = tarotNode.get("nombre").asText();
            String description = tarotNode.get("descripcion").asText();
            int puntos = tarotNode.get("efecto").get("puntos").asInt();
            float mult = tarotNode.get("efecto").get("multiplicador").floatValue();
            String ejemplar = tarotNode.get("ejemplar").asText();

            String objetivo = tarotNode.get("sobre").asText();
            List<ScoringStrategy> listaStrategys = new ArrayList<>();

            if (objetivo.equals("carta")){
                if (mult > 1)
                    listaStrategys.add(new ReplaceMultStrategy(mult));
                else
                    listaStrategys.add(new ReplacePointStrategy(puntos));

                tarots.add(new Tarot(name,description,listaStrategys, player.getPlayingHand()));
            } else {
                listaStrategys.add(new IncreasePointsStrategy(puntos));
                listaStrategys.add(new IncreaseMultStrategy(mult));
                Hand mano = player.getPlayingHand().checkHand(ejemplar);
                tarots.add(new Tarot(name,description,listaStrategys, mano));
            }
        }
        return tarots;
    }

    // Metodo para obtener los Tarots de una ronda
    public List<JokerApply> parseJoker(int roundIndex, Rounds rounds, Player player) {
        List<JokerApply> jokers = new ArrayList<>();
        JsonNode jokersNode = rootNode.get("rondas").get(roundIndex).get("tienda").get("comodines");

        for (JsonNode jokerNode : jokersNode) {

            if (jokerNode.has("comodines")) { // MULTIJOKER
                String name = jokerNode.get("nombre").asText();
                String description = jokerNode.get("descripcion").asText();
                List<JokerApply> jokersApplys = new ArrayList<>();
                for (JsonNode j : jokerNode.get("comodines")) {
                    jokersApplys.add(this.parseJokerApply(j, rounds, player));
                }
                JokerApply jokerMulti = new AlwaysApplyJoker(new Joker(name, description, new MultiJokerStrategy(jokersApplys)));
                jokers.add(jokerMulti);

            } else {
                jokers.add(this.parseJokerApply(jokerNode, rounds, player));
            }
        }
        return jokers;
    }

    // Metodo para obtener las Cartas de una Tienda pertenecientes a una Ronda
    public Card parseStoreCard(int roundIndex) {
        JsonNode storeCardNode = rootNode.get("rondas").get(roundIndex).get("tienda").get("carta");
        return this.parseCard(storeCardNode);
    }

    // Metodo para obtener la Cartas dado un cardNode
    private Card parseCard(JsonNode cardNode) {
        Score score = new Score(
                cardNode.get("puntos").asInt(),
                cardNode.get("multiplicador").asInt(),
                0
        );
        Suit suit = Suit.fromString(cardNode.get("palo").asText());
        Rank rank = Rank.fromString(cardNode.get("numero").asText());
        return new Card(score, suit, rank);
    }

    private JokerApply parseJokerApply(JsonNode jokerNode, Rounds rounds, Player player) {
        String name = jokerNode.get("nombre").asText();
        String description = jokerNode.get("descripcion").asText();
        int puntos = jokerNode.get("efecto").get("puntos").asInt();
        int mult = jokerNode.get("efecto").get("multiplicador").asInt();

        ScoringStrategy scoringStrategy;
        JokerStrategy jokerStrategy;
        JokerApply jokerApply;

        // Obtenemos el ScoringStrategy
        if (puntos > 1) {
            scoringStrategy = new IncreasePointsStrategy(puntos);
        } else {
            if (description.contains("x")) {
                scoringStrategy = new MultiplyMultStrategy(mult);
            } else {
                scoringStrategy = new IncreaseMultStrategy(mult);
            }
        }

        if (jokerNode.get("activacion").isObject()) {

            if (jokerNode.get("activacion").has("Mano Jugada")) {
                String handString = jokerNode.get("activacion").get("Mano Jugada").asText();
                Hand hand = player.getPlayingHand().checkHand(handString);
                jokerStrategy = new HandJokerStrategy(scoringStrategy, player.getPlayingHand(), hand);
                jokerApply = new AlwaysApplyJoker(new Joker(name, description, jokerStrategy));

            } else {
                int chance = jokerNode.get("activacion").get("1 en").asInt();
                Joker joker = new Joker(name, description, new ScoreJokerStrategy(scoringStrategy)); //-----------------------------SUPUESTO---------------------------------------------------

                if (description.contains("destruirse")) {  // -----------------------------SUPUESTO---------------------------------------------------
                    jokerApply = new RandomDestroyJoker(chance, joker, player);
                } else {
                    jokerApply = new RandomApplyJoker(chance, joker);
                }
            }

        } else {
            String activacion = jokerNode.get("activacion").asText();

            if (activacion.equals("Descarte")) {
                jokerStrategy = new DiscardJokerStrategy(scoringStrategy, rounds);
            } else {
                jokerStrategy = new ScoreJokerStrategy(scoringStrategy);
            }
            jokerApply = new AlwaysApplyJoker(new Joker(name, description, jokerStrategy));
        }
        return jokerApply;
    }
}

