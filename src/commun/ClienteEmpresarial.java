package commun;


import java.awt.*;

/**
 * Created by Mónica on 26/04/2017.
 */
public class ClienteEmpresarial extends Cliente {

    protected String nomeGerente;

    public ClienteEmpresarial(String userName, String nomeCliente, int nif, Image foto, Image assinatura,
                              String nomeGerente, boolean isAdmin) {
        super(userName, nomeCliente, nif, isAdmin);
        this.nomeGerente = nomeGerente;
    }


}
