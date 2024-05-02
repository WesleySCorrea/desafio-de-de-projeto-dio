package desafioprojetodio.service.impl;

import desafioprojetodio.model.Cliente;
import desafioprojetodio.model.ClienteRepository;
import desafioprojetodio.model.Endereco;
import desafioprojetodio.model.EnderecoRepository;
import desafioprojetodio.service.ClienteService;
import desafioprojetodio.service.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**Implementação da <b>Strategy</b>, a qual pode ser injetada pelo Spring (Via @Autowired). Essa classe será tratada como  um <b>Singleton</b>.
 *
 * @author Wesley Silvestre.
 */

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private ViaCepService viaCepService;

    @Override
    public Iterable<Cliente> buscarTodos() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente buscaPorId(Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.get();
    }

    @Override
    public void inserir(Cliente cliente) {
        salvarClienteComCep(cliente);
    }

    @Override
    public void atualizar(Long id, Cliente cliente) {
        Optional<Cliente> clienteBd = clienteRepository.findById(id);
        if (clienteBd.isPresent()) {
            salvarClienteComCep(cliente);
        }
    }

    @Override
    public void deletar(Long id) {
        clienteRepository.deleteById(id);
    }

    private void salvarClienteComCep(Cliente cliente) {
        String cep = cliente.getEndereco().getCep();
        Endereco endereco = enderecoRepository.findById(Long.valueOf(cep)).orElseGet(() -> {
            Endereco novoEndereco = viaCepService.consultarCep(String.valueOf(cep));
            enderecoRepository.save(novoEndereco);
            return novoEndereco;
        });
        clienteRepository.save(cliente);
    }
}
