import java.util.Vector;
import java.util.concurrent.Semaphore;

public class Consumidor implements Runnable{
    private Vector<String> armazenamento;
    private Semaphore livre;
    private Semaphore ocupado;

    public Consumidor (Vector<String> armz, Semaphore lvr, Semaphore ocp) throws Exception
    {
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

    private Thread  tarefa = new Thread (this);

    public void start ()
    {
        this.tarefa.start();
    }

    public void join () throws InterruptedException
    {
        this.tarefa.join();
    }

    private boolean fim = false;

    public void morra ()
    {
        this.fim=true;
    }

    public void run ()
    {
        while (!this.fim){
            this.ocupado.acquireUninterruptibly();
            String imprimido = this.armazenamento.get(0);
            this.armazenamento.remove(0);
            this.livre.release();
            System.out.println(imprimido);
            try { this.tarefa.sleep (800); } catch (Exception erro) {}
        }

        while (this.armazenamento.size()!=0){
            this.ocupado.acquireUninterruptibly();
            String imprimido = this.armazenamento.get(0);
            this.armazenamento.remove(0);
            this.livre.release();
            System.out.println(imprimido);
            try { this.tarefa.sleep (800); } catch (Exception erro) {}
        }
    }
}
