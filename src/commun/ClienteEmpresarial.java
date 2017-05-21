package commun;

import java.awt.*;

public class ClienteEmpresarial extends Cliente {

    private String nomeResponsavel;
    //Classificação Portuguesa das Actividades Económicas
    private String cae;

    public ClienteEmpresarial(String userName, String nomeCliente, String nif, Image foto, Image assinatura,
                              String nomeResponsavel, String cae) {
        super(userName, nomeCliente, nif, assinatura, foto, TipoClienteEnum.CLIENTEEMPRESARIAL);
        this.nomeResponsavel = nomeResponsavel;
        this.cae = cae;
    }

    public String getNomeResponsavel() {
        return nomeResponsavel;
    }

    public void setNomeResponsavel(String nomeResponsavel) {
        this.nomeResponsavel = nomeResponsavel;
    }

    public String getCae() {
        return cae;
    }

    public void setCae(String cae) {
        this.cae = cae;
    }
}
