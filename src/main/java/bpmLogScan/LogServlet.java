package bpmLogScan;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by VasilUhnyuck on 6/21/2017.
 */

@WebServlet(
        name = "LogServlet",
        urlPatterns = {"/threads"}
)

public class LogServlet extends HttpServlet {

    private BPMLogScan bpmLogScan = new BPMLogScan();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("searchAction");
        if (action!=null){
            switch (action) {
                case "searchById":
                    //searchEmployeeById(req, resp);
                    System.out.println("By ID");
                    break;
                case "searchByName":
                    //searchEmployeeByName(req, resp);
                    System.out.println("By Name");
                    break;
            }
        }else{
            //List<Employee> result = employeeService.getAllEmployees();
            //forwardListEmployees(req, resp, result)
            //Return all threads
            System.out.println("ALl");
            forwardListThreads(req, resp, bpmLogScan.getBPMLog().getLogFile());
        }
    }

    private void forwardListThreads(HttpServletRequest req, HttpServletResponse resp, LogMessage[] threadList)
            throws ServletException, IOException {
        String nextJSP = "/jsp/list-threads.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
        req.setAttribute("threadList", threadList);
        dispatcher.forward(req, resp);
    }
}
