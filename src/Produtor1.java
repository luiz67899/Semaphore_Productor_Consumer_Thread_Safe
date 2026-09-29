import java.util.Vector;
import java.util.concurrent.Semaphore;

public class Produtor1 extends Thread {
    private Vector<String> armazenamento;
    private Semaphore livre;
    private Semaphore ocupado;

    public Produtor1(Vector<String> armz, Semaphore lvr, Semaphore ocp) throws Exception{
        if (armz==null)
            throw new Exception ("Armazenamento ausente");

        if (lvr==null)
            throw new Exception ("Livre ausente");

        if (ocp==null)
            throw new Exception ("Ocupado ausente");

        this.armazenamento = armz;
        this.livre         = lvr;
        this.ocupado       = ocp;

    }

    private boolean fim = false;

    public void morra(){
        this.fim = true;
    }

    public void run(){
        String imprimir = "[DOC] Relatorio.pdf";
        while (!this.fim){
           this.livre.acquireUninterruptibly();
           this.armazenamento.add(imprimir);
           this.ocupado.release();
           try { this.sleep (100); } catch (Exception erro) {}
        }


    }

}
