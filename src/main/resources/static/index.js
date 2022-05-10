function logginn(){
    const brukernavn=$("#brukernavn").val();
    const passord=$("#passord").val();
    const url="/logginn?brukernavn="+brukernavn+"&passord="+passord;
    $.get(url,function (ok){
        if(ok){
            window.location.href="liste.html";
        }else{
            $("#feilLogginn").html("Feil brukernavn eller passord");
        }
    })
        .fail(function(jqXHR){
            const json=$.parseJSON(jqXHR.responseText);
            $("#feilLogginn").html(json.message);
        });
}