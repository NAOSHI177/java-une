    package POO;

    public class TestAireEmcap {
        public static void main(String[] args) {
                AireAcondicionado ac = new AireAcondicionado();

                ac.mudarTemperatura(21);
                System.out.println("Temperatua: "+ ac.obtenerTemperatura());
            
                ac.mudarTemperatura(10);
                System.out.println("Temperatua: "+ ac.obtenerTemperatura());
            }
        }
