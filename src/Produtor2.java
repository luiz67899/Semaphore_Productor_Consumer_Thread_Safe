import java.util.Vector;
import java.util.concurrent.Semaphore;

public class Produtor2 extends Thread{
    Vector<String> armazenamento;
    Semaphore livre;
    Semaphore ocupado;

    public Produtor2 (Vector<String> armz, Semaphore lvr, Semaphore ocp) throws Exception{
        if (armz==null)
            throw new Exception ("Armazenamento ausente");

        if (lvr==null)
            throw new Exception ("Livre ausente");

        if (ocp==null)
            throw new Exception ("Ocupado ausente");

        this.armazenamento = armz;
        this.livre = lvr;
        this.ocupado = ocp;
    }

    private boolean fim = false;

    public void morra(){
        this.fim = true;
    }

    public void run(){
        String imprimir = "[XLS] Planilha.xlsx";
        while (!fim){
            this.livre.acquireUninterruptibly();
            this.armazenamento.add(imprimir);
            this.ocupado.release();
            try {this.sleep(150);}catch (Exception e){}
        }

    }
}
