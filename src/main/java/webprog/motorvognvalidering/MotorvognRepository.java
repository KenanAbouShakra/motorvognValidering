package webprog.motorvognvalidering;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MotorvognRepository {
    @Autowired
    private JdbcTemplate db;

    private Logger logger = LoggerFactory.getLogger(MotorvognRepository.class);

    public boolean Lagre(MotorvognReg mr){

        String sql="INSERT INTO Bil(personnr, fornavn, etternavn, kjennetegn, bilmerke, bilmodell) VALUES (?,?,?,?,?,?)";
        try {
            db.update(sql, mr.getPersonnr(),mr.getFornavn(), mr.getEtternavn(),mr.getKjennetegn(),mr.getBilmerke(),mr.getBilmodell());
            return true;
        }catch (Exception e){
            logger.error("Feil i lagre()"+e);
            return false;
        }
    }

    public List<MotorvognReg> hentAlle(){
        String sql= "SELECT * FROM Bil";
        try{
            return  db.query(sql, new BeanPropertyRowMapper(MotorvognReg.class));
        }catch (Exception e){
            logger.error("Feil i hentAlle()"+e);
            return null;
        }
    }
    public boolean slettAlle(){
        String sql="Delete  FROM Bil";
        try {
            db.update(sql);
            return true;
        }catch (Exception e){
            logger.error("Feil i slettAlle()"+e);
            return false;
        }
    }
    public MotorvognReg hentForEndring(int id){
        String sql="SELECT * FROM Bil WHERE id=?";
        MotorvognReg enBilReg=db.queryForObject(sql,BeanPropertyRowMapper.newInstance(MotorvognReg.class),id);
        return enBilReg;
    }
    public void endreEnBil(MotorvognReg mr){
        String sql="UPDATE Bil SET personnr=?, fornavn=?, etternavn=?, kjennetegn=?, bilmerke=?, bilmodell=? WHERE id=?";
        db.update(sql, mr.getPersonnr(), mr.getFornavn(), mr.getEtternavn(),mr.getKjennetegn(), mr.getBilmerke(),mr.getBilmodell() , mr.getId());
    }
    public void slettEnBil(int id){
        String sql="DELETE FROM Bil WHERE id=?";
        db.update(sql,id);
    }
    public List<Motorvogn> hentEnBil(){
        String sql="SELECT * FROM Motorvogn";
        List<Motorvogn> alleMotorvogner=db.query(sql, new BeanPropertyRowMapper(Motorvogn.class));
        return alleMotorvogner;
    }
    public boolean logginn(String brukernavn, String passord){
        String sql="SELECT COUNT(*) FROM Bruker WHERE brukernavn=? AND passord=? ";
        try{
            int funnet=db.queryForObject(sql, Integer.class, brukernavn, passord);
            if(funnet>0){
                return true;
            }else{
                return false;
            }
        }catch (Exception e){
            return false;
        }

    }

}
