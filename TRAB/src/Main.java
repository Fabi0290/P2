import java.util.*;
import model.Armazem;
import model.Mercadoria;
import model.TagIoT;
import model.Transporte;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Listas para armazenar armazéns, transportes e mercadorias
        List<Armazem> armazens = new ArrayList<>();
        List<Transporte> transportes = new ArrayList<>();
        List<Mercadoria> mercadorias = new ArrayList<>();

        // Inicializando com um armazém, um transporte e uma mercadoria
        Armazem armazemInicial = new Armazem("Armazém Central", "Rua Principal, 100", 5000, 2000, false);
        armazens.add(armazemInicial);

        Transporte transporteInicial = new Transporte("Caminhão 1", 10000, 5000, true);
        transportes.add(transporteInicial);

        TagIoT tagInicial = new TagIoT("TAG001");
        Mercadoria mercadoriaInicial = new Mercadoria("M001", "Caixa de Vidro", "Frágil", 50, 20, tagInicial, null);
        mercadorias.add(mercadoriaInicial);

        System.out.println("Sistema inicializado com 1 armazém, 1 transporte e 1 mercadoria.");

        while (true) {
            System.out.println("\n--- Menu Principal ---");
            System.out.println("1. Criar Armazém");
            System.out.println("2. Criar Meio de Transporte");
            System.out.println("3. Criar Mercadoria e associar Tag IoT");
            System.out.println("4. Registrar Mercadoria em Armazém");
            System.out.println("5. Listar Mercadorias por Localização");
            System.out.println("6. Listar Armazéns");
            System.out.println("7. Listar Transportes");
            System.out.println("8. Listar Mercadorias");
            System.out.println("9. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = 0;
            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida. Por favor, insira um número.");
                continue;
            }

            switch (opcao) {
                case 1:
                    System.out.print("Digite o nome do armazém: ");
                    String nomeArmazem = scanner.nextLine();
                    System.out.print("Digite o endereço do armazém: ");
                    String endereco = scanner.nextLine();
                    System.out.print("Capacidade máxima do armazém: ");
                    int capacidade = Integer.parseInt(scanner.nextLine());
                    System.out.print("Peso máximo suportado: ");
                    int pesoMaximo = Integer.parseInt(scanner.nextLine());

                    Armazem armazem = new Armazem(nomeArmazem, endereco, capacidade, pesoMaximo, false);
                    armazens.add(armazem);
                    System.out.println("Armazém criado com sucesso!");
                    break;

                case 2:
                    System.out.print("Digite o nome do transporte: ");
                    String nomeTransporte = scanner.nextLine();
                    System.out.print("Capacidade máxima do transporte: ");
                    int capacidadeTransporte = Integer.parseInt(scanner.nextLine());
                    System.out.print("Peso máximo suportado pelo transporte: ");
                    int pesoMaxTransporte = Integer.parseInt(scanner.nextLine());
                    System.out.print("É externo? (true/false): ");
                    boolean externo = Boolean.parseBoolean(scanner.nextLine());

                    Transporte transporte = new Transporte(nomeTransporte, capacidadeTransporte, pesoMaxTransporte, externo);
                    transportes.add(transporte);
                    System.out.println("Transporte criado com sucesso!");
                    break;

                case 3:
                    System.out.print("Digite o ID da mercadoria: ");
                    String idMercadoria = scanner.nextLine();
                    System.out.print("Digite o nome da mercadoria: ");
                    String nomeMercadoria = scanner.nextLine();
                    System.out.print("Descrição da mercadoria: ");
                    String descricao = scanner.nextLine();
                    System.out.print("Volume da mercadoria: ");
                    int volume = Integer.parseInt(scanner.nextLine());
                    System.out.print("Peso da mercadoria: ");
                    int peso = Integer.parseInt(scanner.nextLine());

                    System.out.print("Digite o ID da Tag IoT: ");
                    String idTag = scanner.nextLine();
                    TagIoT tag = new TagIoT(idTag);

                    Mercadoria mercadoria = new Mercadoria(idMercadoria, nomeMercadoria, descricao, volume, peso, tag, null);
                    mercadorias.add(mercadoria);
                    System.out.println("Mercadoria e Tag IoT criadas com sucesso!");
                    break;

                case 4:
                    if (armazens.isEmpty()) {
                        System.out.println("Nenhum armazém disponível. Crie um antes de registrar mercadorias.");
                        break;
                    }

                    if (mercadorias.isEmpty()) {
                        System.out.println("Nenhuma mercadoria disponível. Crie uma antes de registrar.");
                        break;
                    }

                    System.out.println("Escolha um armazém:");
                    for (int i = 0; i < armazens.size(); i++) {
                        System.out.println((i + 1) + ". " + armazens.get(i));
                    }

                    int armazemEscolhido;
                    try {
                        armazemEscolhido = Integer.parseInt(scanner.nextLine()) - 1;
                        if (armazemEscolhido < 0 || armazemEscolhido >= armazens.size()) {
                            throw new IndexOutOfBoundsException();
                        }
                    } catch (Exception e) {
                        System.out.println("Escolha inválida. Tente novamente.");
                        break;
                    }

                    System.out.println("Escolha uma mercadoria para registrar:");
                    for (int i = 0; i < mercadorias.size(); i++) {
                        System.out.println((i + 1) + ". " + mercadorias.get(i));
                    }

                    int mercadoriaEscolhida;
                    try {
                        mercadoriaEscolhida = Integer.parseInt(scanner.nextLine()) - 1;
                        if (mercadoriaEscolhida < 0 || mercadoriaEscolhida >= mercadorias.size()) {
                            throw new IndexOutOfBoundsException();
                        }
                    } catch (Exception e) {
                        System.out.println("Escolha inválida. Tente novamente.");
                        break;
                    }

                    Armazem armazemSelecionado = armazens.get(armazemEscolhido);
                    Mercadoria mercadoriaSelecionada = mercadorias.get(mercadoriaEscolhida);

                    if (armazemSelecionado.adicionarMercadoria(mercadoriaSelecionada)) {
                        System.out.println("Mercadoria registrada no armazém!");
                    } else {
                        System.out.println("Falha ao registrar a mercadoria no armazém.");
                    }
                    break;

                case 5:
                    System.out.print("Digite a localização para consulta (nome do armazém ou transporte): ");
                    String localizacao = scanner.nextLine();

                    List<TagIoT> tagsEncontradas = TagIoT.buscarPorLocalizacao(localizacao);
                    if (tagsEncontradas == null || tagsEncontradas.isEmpty()) {
                        System.out.println("Nenhuma mercadoria encontrada nesta localização.");
                    } else {
                        System.out.println("Mercadorias encontradas:");
                        tagsEncontradas.forEach(tagIoT -> System.out.println("Tag: " + tagIoT));
                    }
                    break;

                case 6:
                    System.out.println("\n--- Lista de Armazéns ---");
                    if (armazens.isEmpty()) {
                        System.out.println("Nenhum armazém disponível.");
                    } else {
                        armazens.forEach(System.out::println);
                    }
                    break;

                case 7:
                    System.out.println("\n--- Lista de Transportes ---");
                    if (transportes.isEmpty()) {
                        System.out.println("Nenhum transporte disponível.");
                    } else {
                        transportes.forEach(System.out::println);
                    }
                    break;

                case 8:
                    System.out.println("\n--- Lista de Mercadorias ---");
                    if (mercadorias.isEmpty()) {
                        System.out.println("Nenhuma mercadoria disponível.");
                    } else {
                        mercadorias.forEach(System.out::println);
                    }
                    break;

                case 9:
                    System.out.println("Saindo da aplicação. Até logo!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }
    }
}