package commun;

import java.awt.*;
import java.time.LocalDate;

public class ClienteIndividual extends Cliente {

    private String numCartaoCidadao;
    private String numPassaporte;
    private LocalDate dataDeNascimento;

    public ClienteIndividual(String userName, String nomeCliente, String idCliente, String nif, String morada, String numTelefone, Image foto, Image assinatura,
                             String numCartaoCidadao, String numPassaporte, LocalDate dataDeNascimento) {
        super(userName, nomeCliente, idCliente, nif, morada, numTelefone, assinatura, foto, TipoClienteEnum.CLIENTEINDIVIDUAL);
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
