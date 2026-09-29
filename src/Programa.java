import java.util.Vector;
import java.util.concurrent.Semaphore;

public class Programa {
    public static void main(String[] args){
        try {
            Vector<String> armazenamento;
            Semaphore livre = new Semaphore(3,true);
            Semaphore ocupado = new Semaphore(0,true);
            System.out.println ("Tecle ENTER para ativar as tarefas e");
            System.out.println ("Tecle novamente ENTER para terminar o programa.");

            Teclado.getUmString();



        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
