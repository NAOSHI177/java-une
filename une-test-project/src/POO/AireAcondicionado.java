package POO;

public class AireAcondicionado {
    private int temperatura;
    public void mudarTemperatura(int temperatura) {
        if(temperatura >= 17 && temperatura <=25){
            this.temperatura = temperatura;
        }
        
    }
    public int obtenerTemperatura(){
        return temperatura;
    }

}
