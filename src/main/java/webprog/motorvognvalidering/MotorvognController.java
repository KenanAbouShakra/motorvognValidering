package webprog.motorvognvalidering;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
public class MotorvognController {
   private Logger logger= LoggerFactory.getLogger(MotorvognController.class);
    @Autowired
    private MotorvognRepository rep;

    private boolean validerMotorvogn(MotorvognReg motorvogn){
        String regexPersonnr="[0-9]{11}";
        String regexFornavn="[a-zæøåA-ZÆØÅ.\\- ]{2,30}";
        String regexEtterNavn="[a-zæøåA-ZÆØÅ.\\- ]{2,30}";
        String regexKjennetegn="[A-Z][A-Z][0-9]{5}";
        boolean personnrOk=String.valueOf(motorvogn.getPersonnr()).matches(regexPersonnr);
        boolean fornavnOk=motorvogn.getFornavn().matches(regexFornavn);
        boolean etternavnOk=motorvogn.getEtternavn().matches(regexEtterNavn);
        boolean kjennetegnOk=motorvogn.getKjennetegn().matches(regexKjennetegn);
        if(personnrOk && fornavnOk && etternavnOk && kjennetegnOk){
            return true;
        }
            logger.error("Valideringsfeil");
            return false;
    }
    @PostMapping("/lagre")
    public void lagreKunde(MotorvognReg mr, HttpServletResponse response) throws IOException {
        if(!validerMotorvogn(mr)){
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Feil i validering- prøv igjen senere");
        }
        if(!rep.Lagre(mr)){
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Feil i DB, prøv igjen senere");
        }
    }

    @GetMapping("hentBil")
    public List<Motorvogn> hentEnBil() {
        List<Motorvogn> alleBiler=rep.hentEnBil();
        return alleBiler;
    }
    @GetMapping("/hentAlle")
    public List<MotorvognReg> hentAlle(HttpServletResponse response) throws IOException{
        List<MotorvognReg> alleRegister=rep.hentAlle();
        if(alleRegister==null){
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Feil i DB, prøv igjen senere");
        }
        return alleRegister;
    }
    @GetMapping("hentForEndring")
    public MotorvognReg hentForEndring(int id){
        return rep.hentForEndring(id);
    }
    @PostMapping("/endreEnBil")
    public void endreEnBil(MotorvognReg mr) {
        if(validerMotorvogn(mr)){
            rep.endreEnBil(mr);
        }
       logger.error("Feil i validering- prøv igjen senere");
    }
    @GetMapping("/slettEnBil")
    public void slettEnBil(int id){
        rep.slettEnBil(id);
    }
    @GetMapping("/slettAlle")
    public void slettAlle(HttpServletResponse response) throws IOException{
        if(!rep.slettAlle()){
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Feil i DB, prøv igjen senere");
        }
    }
}
