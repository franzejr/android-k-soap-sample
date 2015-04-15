package great.ufc.br.k_soap_sample;


import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;


public class SoapRequests {

    private static final String URL ="http://192.168.1.122/HelloWorld";
    private static final String NAMESPACE ="great.ufc.br.k_soap_sample";
    private static final String ACTION = "sayHelloWorld";

    public boolean sendMessage(String msg){

        SoapObject soapObject = new SoapObject(NAMESPACE, ACTION);

        SoapObject usr = new SoapObject(NAMESPACE, "message");
        usr.addAttribute("message", msg);

        soapObject.addSoapObject(usr);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

        envelope.implicitTypes = true;

        HttpTransportSE http = new HttpTransportSE(URL);

        try {
            http.call(ACTION, envelope);

            SoapPrimitive response = (SoapPrimitive) envelope.getResponse();

            return Boolean.parseBoolean(response.toString());

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
