package commun;

import java.awt.*;
import java.time.LocalDate;

/**
 * Created by Mónica on 26/04/2017.
 */
public class ClienteIndividual extends Cliente {

    private String numCartaoCidadao;
    private String numPassaporte;
    private LocalDate dataDeNascimento;

    public ClienteIndividual(String nomeCliente, int nif, Image foto, Image assinatura, String numCartaoCidadao, String numPassaporte, LocalDate dataDeNascimento) {
        super(nomeCliente, nif, foto, assinatura);
        this.numCartaoCidadao = numCartaoCidadao;
        this.numPassaporte = numPassaporte;
        this.dataDeNascimento = dataDeNascimento;
    }

    public String getNumCartaoCidadao() {
        return numCartaoCidadao;
    }

    public void setNumCartaoCidadao(String numCartaoCidadao) {
        this.numCartaoCidadao = numCartaoCidadao;
    }

    public String getNumPassaporte() {
        return numPassaporte;
    }

    public void setNumPassaporte(String numPassaporte) {
        this.numPassaporte = numPassaporte;
    }

    public LocalDate getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(LocalDate dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }
}
