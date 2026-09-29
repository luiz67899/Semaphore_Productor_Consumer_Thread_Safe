import java.util.Vector;
import java.util.concurrent.Semaphore;

public class Programa {
    public static void main(String[] args){
        try {
            Vector<String> armazenamento;
            armazenamento = new Vector<String>();
            Semaphore livre = new Semaphore(3,true);
            Semaphore ocupado = new Semaphore(0,true);
            System.out.println ("Tecle ENTER para ativar as tarefas e");
            System.out.println ("Tecle novamente ENTER para terminar o programa.");

            Teclado.getUmString();
            Produtor1 p1 = new Produtor1(armazenamento,livre,ocupado);
            p1.start();

            Produtor2 p2 = new Produtor2(armazenamento,livre,ocupado);
            p2.start();

            Consumidor c = new Consumidor(armazenamento,livre,ocupado);
            c.start();

            Teclado.getUmString();
            p1.morra();
            p2.morra();
            c.morra();

            p1.join();
            p2.join();
            c.join();

            System.out.println ("Execucao do programa finalizada.");

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
