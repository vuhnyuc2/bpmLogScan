package bpmLogScan;


import java.util.Optional;

/**
 * Created by VasilUhnyuck on 6/21/2017.
 */
public class Main {

    public static final Optional<String> PORT = Optional.ofNullable(System.getenv("PORT"));
    public static final Optional<String> HOSTNAME = Optional.ofNullable(System.getenv("HOSTNAME"));

    public static void main(String[] args) throws Exception {

        System.out.println("IS RUNNINGa");

    }
}
