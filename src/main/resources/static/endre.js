$(function (){
    hentBilene();
    hentForEndring ()
});
function hentBilene(){
    $.get("/hentBil", function (bilene){
        visMerke(bilene);
    });
}
function visMerke(bilene){
    let Merke="<select id='velgBilmerke' onchange='hentModell()'>"+"<option></option>"
    let merkListe=[""]
    let bol=true;
    for(let bil of bilene){
        if(merkListe.includes(bil.merke)){
            bol=false;
        }else{
            bol=true;
            Merke+="<option value='"+bil.merke+"'>"+bil.merke+"</option>"
            merkListe.push(bil.merke)
        }
    }
    Merke+="</select> " ;
    $("#bilmerke").html(Merke);
}
function hentModell(){
    const xmerke=$("#velgBilmerke").val();
    $.get("/hentBil", function (bilene){
        visModell(bilene,xmerke)
    });
}
function visModell(bilene,xmerke){
    let Modell="<select id='velgBilmodell'>"+"<option></option>"
    for(let bil of bilene){
        if(xmerke===bil.merke){
            Modell+="<option value='"+bil.modell+"'>"+bil.modell+"</option>"
        }
    }
    Modell+="</select>";
    $("#bilmodell").html(Modell);
}
function hentForEndring () {
    const id=window.location.search.substring(1);
    const url = "/hentForEndring?id="+id;
    $.get(url, function (bil) {
        $("#id").val(bil.id);
        $("#personnr").val(bil.personnr);
        $("#fornavn").val(bil.fornavn);
        $("#etternavn").val(bil.etternavn);
        $("#kjennetegn").val(bil.kjennetegn);
        $("#velgBilmerke").val(bil.bilmerke);
        $("#velgBilmodell").val(bil.bilmodell);
    });
}

function regEndre(){
    const Bil={
        id: $("#id").val(),
        personnr: $("#personnr").val(),
        fornavn: $("#fornavn").val(),
        etternavn: $("#etternavn").val(),
        kjennetegn: $("#kjennetegn").val(),
        bilmerke: $("#velgBilmerke").val(),
        bilmodell: $("#velgBilmodell").val()
    }
    if(validering()){
        const  url="/endreEnBil?"
        $.post(url,Bil,function (){window.location.href="/";
        });
    }
}