/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author PC
 */
@WebServlet(urlPatterns = {"/CalendarioAniversario"})
public class CalendarioAniversario extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            
            //armazena parametros POST da pagina anterior
            String mes_aniversario = request.getParameter("mes");
            String ano_aniversario = request.getParameter("ano");
             
            
            //Parametriza o formato de data aceito
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-dd");
            //Cria Date com os parametros recebidos
            Date data = sdf.parse(ano_aniversario + "-" + mes_aniversario + "-1");
            Calendar calendar = Calendar.getInstance();
            //Cria um Calendar com a data recebida
            calendar.setTime(data);
            
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>M??s do seu Aniversario</title>");  
            out.println("<style>table{margin:auto;}h1{text-align: center;}td{height: 100px;width: 100px;text-align:center;}</style>");  
            out.println("</head>");
            out.println("<body>");
                                //traduz o M??s recebido                   //Escreve o Ano recebido
            out.println("<h1>" + mes(calendar.get(calendar.MONTH))+"  "+ calendar.get(Calendar.YEAR)+ "</h1>");
            out.println("<table border=1>");
            out.println("<tr>\n" +
                        "    <th>Domingo</th>\n" +
                        "    <th>Segunda-Feira</th> \n" +
                        "    <th>Ter??a-Feira</th>\n" +
                        "    <th>Quarta-Feira</th>\n" +
                        "    <th>Quinta-Feira</th>\n" +
                        "    <th>Sexta-Feira</th>\n" +
                        "    <th>S??bado</th>\n" +
                        "  </tr>");
            
            //Fun????o que cria a tabela de calendario
            out.println(imprime_calendario(calendar));
            
            out.println("</table>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    
    //Fun????o de Tradu????o do M??s
    private String mes(int m){
     System.out.println("Mes p??s calendar:"+m);
       switch(m) {
        case 1:
          return "Fevereiro";         
        case 2:
          return "Mar??o";
        case 3:
          return "Abril";
        case 4:
          return "Maio";
        case 5:
            return "Junho";
        case 6:
            return "Julho";
        case 7:
            return "Agosto";
        case 8:
            return "Setembro";
        case 9:
            return "Outubro";
        case 10:
            return "Novembro";
        case 11:
            return "Dezembro";
        case 0:
          return "Janeiro";
        default:
          return "M??s invalido";
      }
    
    }
    
    
    private String imprime_calendario(Calendar data){
    
        //Pega o ultimo dia em que o m??s termina
        int ultimo_dia = data.getActualMaximum(Calendar.DATE);
        //Pega a quantidade de semanas que o m??s escolhido possui
        int qt_semanas = data.getActualMaximum(Calendar.WEEK_OF_MONTH);
        //Pega o dia da semana em que come??a o m??s
        int dia_semana_comeco = data.get(Calendar.DAY_OF_WEEK);
        //Verifica se o dia ?? igual a 1 pois a fun????o retorna 1 se for s??bado
        if(dia_semana_comeco == 1){
            //definido 6 para sabado pois nosso calendario come??a no domingo e termina aos sabados
            dia_semana_comeco = 6;
        }else{
        //subritraimos 1 para decrementar o valor do sabado de nossa semana
        dia_semana_comeco -= 1; 
        }
        
        //Inicia a string que armazenara as linhas e colunas do calendario
        String calendario = "";
        //variavel contador de dias
        int dia = 1;
        //loop de semanas
        for (int i = 0; i < qt_semanas; i++) {
            //String que armazena a linha de semana
            String semana = "<tr>";
            //loop para preencher os dias da semana
            for (int j = 0; j < 7; j++) {
                //verifica se ?? a primeira semana
                if(i == 0){
                    //verifica se ?? menos que o dia da semana em que come??a o m??s
                    if(j < dia_semana_comeco){
                        semana += "<td></td>";
                    }else{
                        //preenche os dias da primeira semana
                        semana += "<td>"+ dia +"</td>";
                        dia++;//incrementa o dia 
                    }
                }else{
                    //verifica se o loop j?? chegou no ultimo dia do m??s
                    //se sim ele preencher?? o resto com colunas vazias 
                    if( dia > ultimo_dia){
                        semana += "<td></td>";
                    }else{
                    //preenche com os dias seguintes
                    semana += "<td>"+ dia +"</td>";
                    dia++;
                    }
                }
            }
            semana += "</tr>";
            calendario += semana;
            
        }
        //retorna as linhas e colunas do calendario
        return calendario;
    }
    
   
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
