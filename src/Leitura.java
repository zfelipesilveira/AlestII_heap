import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;


public class Leitura {


    public static void main(String agrs[]){

        //leEntrada("entrada.txt");




        //MaxHeap compras = new MaxHeap()
        //MinHeap vendas = new MinHeap()




        int contVendas = 0;
        int contCompras = 0;




        String linhas[] = new String[1000000];
        int numLinhas = 0;

        Path path1 = Paths.get("operacoes.txt"); // localiza o arquivo que será lido

        try (BufferedReader reader = Files.newBufferedReader(path1, Charset.defaultCharset())) {
            String line = null;
            int heapSize = Integer.parseInt(reader.readLine());
            PriorityQueue<String[]> compras = new PriorityQueue<>(heapSize, Comparator.comparingInt(x -> -Integer.parseInt(x[2]))); //MaxHeap
            PriorityQueue<String[]> vendas = new PriorityQueue<>(heapSize, Comparator.comparingInt(x -> Integer.parseInt(x[2]))); //MinHeap
            int lucroFinal = 0;
            int numeroAcoes = 0;

            while ((line = reader.readLine()) != null) {
                if(line.startsWith("C")){
                    compras.add(line.split(" "));

                }
                if(line.startsWith("V")){
                    vendas.add(line.split(" "));

                }
                while(compras.size()>0 && vendas.size()>0 && Integer.parseInt(compras.peek()[2]) >= Integer.parseInt(vendas.peek()[2])){
                    if (Integer.parseInt(compras.peek()[1])> Integer.parseInt((vendas.peek()[1]))){
                        lucroFinal = lucroFinal + (Integer.parseInt(compras.peek()[2] ) - Integer.parseInt(vendas.peek()[2])) * Integer.parseInt(vendas.peek()[1]);
                        numeroAcoes = numeroAcoes + Integer.parseInt(vendas.peek()[1]);
                        compras.peek()[1] = String.valueOf(Integer.parseInt(compras.peek()[1]) - Integer.parseInt(vendas.peek()[1]));
                        vendas.poll();


                        // ajustar quantidade de acoes que sobraram das compras
                        //ajustar o a diferenca do peek[1] das compras pelo peek[1] das vendas
                        //acumular numero de acoes pela quantidade de acoes de vendas (peek[1] das vendas multiplicado por 2)
                        //remover o top das vendas (vendas.poll())
                    }
                    else if (Integer.parseInt(compras.peek()[1]) < Integer.parseInt((vendas.peek()[1]))){
                        lucroFinal = lucroFinal + (Integer.parseInt(compras.peek()[2] ) - Integer.parseInt(vendas.peek()[2])) * Integer.parseInt(compras.peek()[1]);
                        numeroAcoes = numeroAcoes + Integer.parseInt(compras.peek()[1]);
                        vendas.peek()[1] = String.valueOf(Integer.parseInt(vendas.peek()[1]) - Integer.parseInt(compras.peek()[1]));
                        compras.poll();

                    }
                    else {
                        lucroFinal = lucroFinal + (Integer.parseInt(compras.peek()[2] ) - Integer.parseInt(vendas.peek()[2])) * Integer.parseInt(vendas.peek()[1]);
                        compras.peek()[1] = String.valueOf(Integer.parseInt(compras.peek()[1]) - Integer.parseInt(vendas.peek()[1]));
                        compras.poll();
                    }



                }



            }

            System.out.println("O lucro final obtido é de " + lucroFinal + " reais");
            System.out.println("O número de ações negociadas é de " + numeroAcoes);
            System.out.println("Ainda restaram " + vendas.size() + " ordens de vendas e " + compras.size() + " ordens de compras");



            //System.out.println(vendas.size());


            //System.out.println(lucroFinal);
            //System.out.println(numeroAcoes);

            //System.out.println(lucroFinal);
            //System.out.println("Teste");

            System.out.println("teste");



        } catch (IOException e) {
            System.err.format("Erro na leitura do arquivo: ", e);
        }



    }





}
