$(function (){
    hentAlle();
    hentBilene();
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
function reg(){
    const Bil={
        personnr: $("#personnr").val(),
        fornavn: $("#fornavn").val(),
        etternavn: $("#etternavn").val(),
        kjennetegn: $("#kjennetegn").val(),
        bilmerke: $("#velgBilmerke").val(),
        bilmodell: $("#velgBilmodell").val()
    }
    if(validering()){
        $.post("/lagre", Bil, function(){
            hentAlle();
        });
        $("#personnr").val("")
        $("#fornavn").val("")
        $("#etternavn").val("")
        $("#kjennetegn").val("")
        $("#velgBilmerke").val("")
        $("#velgBilmodell").val("");
    }

}
function hentAlle(){
    $.get("/hentAlle",function (data){
        formaterData(data);
    })
        .fail(function(jqXHR){
            const json=$.parseJSON(jqXHR.responseText);
            $("#feil").html(json.message);
        });
}
function formaterData(data){
    let ut="<table><tr><th>Personnummer</th><th>Fornavn</th><th>Etternavn</th><th>Kjennetegn</th><th>Bilmerke</th><th>Bilmodell</th><th></th></tr>"
    for ( const b of data){
        ut+="<tr><td>"+b.personnr+"</td><td>"+b.fornavn+"</td><td>"+b.etternavn+"</td><td>"+b.kjennetegn+"</td><td>"+b.bilmerke+"</td><td>"+b.bilmodell+"</td>"+
            "<td><a class='btn btn-info' href='/endre.html?"+b.id+"'>Endre</a> </td>"+
            "<td><button class='btn btn-danger' onclick='slettEnBil("+b.id+")'>Slett</button> </td></tr>";
    }
    ut+="</table>"
    $("#output").html(ut);
}

function slettEnBil(id){
    const url="/slettEnBil?id="+id;
    $.get(url,function (){
        hentAlle();
        window.location.herf="liste.html";
    });
}
function slettAlleBilene(){
    $.get("/slettAlle",function (){
        hentAlle();
    });

}
function loggut(){
    $.get("/loggut", function (){
        window.location.href="/";
    });
}