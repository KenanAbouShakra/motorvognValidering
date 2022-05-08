function validerPresonnr(){
    const regex=/^[0-9]{11}$/
    const ok=regex.test($("#personnr").val())
    if(!ok){ $("#feilPersonnr").html("feil personnr, personnr må være 11 siffer")
        return false;
    }
    $("#feilPersonnr").html("")
    return true;
}
function validerFornavn(){
    const regex=/^[a-zæøåA-ZÆØÅ. ,\-]{2,30}$/
    const ok=regex.test($("#fornavn").val())
    if(!ok){
        $("#feilFornanv").html("Feil Fornavn, Fornavn må være mellem 2 og 30 bokstaver")
        return false;
    }
    $("#feilFornanv").html("")
    return true;
}
function validerEtternavn(){
    const regex=/^[a-zæøåA-ZÆØÅ. ,\-]{2,30}$/
    const ok=regex.test($("#etternavn").val())
    if(!ok){
        $("#feilEtternavn").html("feil Etternavn, etternavn må være mellom 2 og 30 bokstaver")
        return false;
    }
     $("#feilEtternavn").html("")
    return true;
}
function validerKjennetegn(){
    const regex=/^[A-Z][A-Z][0-9]{5}$/
    const ok=regex.test($("#kjennetegn").val())
    if(!ok){
        $("#feilKjennetegn").html("Feil kjennetegn");
        return false;
    }
     $("#feilKjennetegn").html("");
    return true;
}
function validering() {
    const personnrOk = validerPresonnr();
    const fornavnOk = validerFornavn();
    const etternavnOk = validerEtternavn();
    const kjennetegnOk = validerKjennetegn();
    if (personnrOk && fornavnOk && etternavnOk && kjennetegnOk) {
        return true;
    }
}
