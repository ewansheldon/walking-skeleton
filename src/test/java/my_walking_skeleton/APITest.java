package my_walking_skeleton;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class APITest {
    @BeforeEach
    void setUp() {
        App.main(new String[0]);
    }

    @AfterEach
    void tearDown() {
        App.closeServer();
    }

    @Test
    void gets_home_route() {
        given().port(App.getPort()).get().then().assertThat().body(is("hello world"));
    }

    @Test
    void gets_ewan_route() {
        given().port(App.getPort()).get("/ewan").then().assertThat().body(is("hello ewan"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"fabio", "ignacio", "martin", "maciej"})
    void says_hello_to_anyone(String name) {
        String path = String.format("/%s", name);
        String expected = String.format("hello %s", name);
        given().port(App.getPort()).get(path).then().assertThat().body(is(expected));
    }
}
