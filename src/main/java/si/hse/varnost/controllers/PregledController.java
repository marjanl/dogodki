package si.hse.varnost.controllers;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import si.hse.varnost.ejb.OstaloEjb;
import si.hse.varnost.ejb.PorociloEjb;
import si.hse.varnost.model.Porocilo;

@ViewScoped
@Named("pregledCtrl")
public class PregledController implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	PorociloEjb ejb;
	@Inject
	OstaloEjb ostaloEjb;
	
	private static final String REPORT = "dogodekReport.jasper";
	
	private List<Porocilo> porocila;
	private Porocilo selected;

	@PostConstruct
	public void init() {
		porocila = ejb.findAll();
	}
	
	public void pdf(Porocilo porocilo) {
		
		if(porocilo == null || porocilo.getId() == null) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ni poročila za print!?", "Shrani poročilo, nato printaj"));
			return;
		}
		
		InitialContext context = null;
        Connection con = null;
        InputStream is = null;
        ServletOutputStream os= null;
        try {
            context = new InitialContext();
            DataSource dataSource = (DataSource) context.lookup("java:/hseDS");
            con = dataSource.getConnection();

            Map<String, Object> param = new HashMap<>();
            param.put("id_porocilo", porocilo.getId().intValue());

            is = this.getClass().getClassLoader().getResourceAsStream(REPORT);
            JasperPrint jasperPrint = JasperFillManager.fillReport(is, param, con);

            HttpServletResponse httpServletResponse=(HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();  
            httpServletResponse.addHeader("Content-disposition", "attachment; filename=porocilo_"+porocilo.getId()+".pdf");  
            os=httpServletResponse.getOutputStream();  
            JasperExportManager.exportReportToPdfStream(jasperPrint, os);  
            System.out.println("report narejen =>"+porocilo.getId());
            FacesContext.getCurrentInstance().responseComplete();  
            
        }
        catch (JRException | NamingException | SQLException | IOException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Napaka pri printanju", e.getLocalizedMessage()));
            System.out.println("    Exception run report " + REPORT + ":");
            e.printStackTrace();
        }
        finally {
            try {
                if (null != con)
                    con.close();
            } catch (SQLException e) {
                System.out.println("Exception while closing connection:" + e.getMessage());
            }
            try {
                if (null != context)
                    context.close();
            } catch (NamingException e) {
                System.out.println("Exception while closing context:" + e.getMessage());
            }
            try {
                if (null != is)
                    is.close();
            } catch (IOException e) {
                System.out.println("Exception while closing is:" + e.getMessage());
            }
            try {
                if (null != os)
                    os.close();
            } catch (IOException e) {
                System.out.println("Exception while closing os:" + e.getMessage());
            }            
        }
	}
	
	public List<Porocilo> getPorocila() {
		return porocila;
	}

	public void setPorocila(List<Porocilo> porocila) {
		this.porocila = porocila;
	}
	
	public Porocilo getSelected() {
		return selected;
	}

	public void setSelected(Porocilo selected) {
		this.selected = selected;
	} 
	
	
}
