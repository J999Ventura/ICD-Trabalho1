package commun;


import java.awt.*;

/**
 * Created by Mónica on 26/04/2017.
 */
public class ClienteEmpresarial extends Cliente {

    protected String nomeGerente;

    public ClienteEmpresarial(String nomeCliente, int nif, Image foto, Image assinatura, String nomeGerente) {
        super(nomeCliente, nif, foto, assinatura);
        this.nomeGerente = nomeGerente;
    }


}
