import spark.Spark;

import static spark.Spark.get;

public class App {
    private static final int DEFAULT_PORT = 8080;

    public static void main(String[] args) {
        Spark.port(getPort());

        get("/", (req, res) -> "hello world");
        get("/ewan", (req, res) -> "hello ewan");
        get("/:name", (req, res) -> String.format("hello %s", req.params("name")));
    }

    public static int getPort() {
        String port = System.getenv("PORT");
        if (port != null)
            return Integer.parseInt(port);
        return DEFAULT_PORT;
    }

    public static void closeServer() {
        Spark.stop();
        Spark.awaitStop();
    }
}
