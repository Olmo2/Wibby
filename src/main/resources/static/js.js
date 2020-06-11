var visible = false;

$(".menu-toggle").click(function(e) {

    e.preventDefault();
    $("#wrapper").toggleClass("toggled");
    visible = !visible;
});

$("#page-body").click(function() {
    if (visible) {
        visible = false;
        $("#wrapper").toggleClass("toggled");
    }

});

$("#collapsibleNavbar").click(function() {
    if (visible) {
        visible = false;
        $("#wrapper").toggleClass("toggled");
    }

});


//PLATAFORMAS
$("#editarPlataforma").on("show.bs.modal", function(event) {
    var button = $(event.relatedTarget) // Button that triggered the modal
        // Extract info from data-* attributes
    var nombre = button.data("nombre")
    var id = button.data("id");
    var desc = button.data("desc");
    var anio = button.data("anio");
    var modal = $(this)

    modal.find(".modal-title").text("Editar plataforma: " + nombre)
    modal.find(".modal-body #ID").val(id);
    modal.find(".modal-body #nombre").val(nombre);
    modal.find(".modal-body #desc").val(desc);
    modal.find(".modal-body #anio").val(anio);
});

//DISTRIBUIDORES
$("#editarDist").on("show.bs.modal", function(event) {
    var button = $(event.relatedTarget) // Button that triggered the modal
    var nombre = button.data("nombre")
    var id = button.data("id");
    var pais = button.data("pais");
    var desc = button.data("desc");
    var anio = button.data("anio");


    var modal = $(this)
    modal.find(".modal-title").text("Editar Juego: " + nombre)
    modal.find(".modal-body #ID").val(id);
    modal.find(".modal-body #nombre").val(nombre);
    modal.find(".modal-body #pais").val(pais);
    modal.find(".modal-body #desc").val(desc);
    modal.find(".modal-body #anio").val(anio);


});

//DESARROLLADORES
$("#editarDesa").on("show.bs.modal", function(event) {
    var button = $(event.relatedTarget) // Button that triggered the modal
    var nombre = button.data("nombre")
    var id = button.data("id");
    var pais = button.data("pais");
    var desc = button.data("desc");
    var anio = button.data("anio");


    var modal = $(this)
    modal.find(".modal-title").text("Editar Juego: " + nombre)
    modal.find(".modal-body #ID").val(id);
    modal.find(".modal-body #nombre").val(nombre);
    modal.find(".modal-body #pais").val(pais);
    modal.find(".modal-body #desc").val(desc);
    modal.find(".modal-body #anio").val(anio);


});

//JUEGOS
$("#editarJuego").on("show.bs.modal", function(event) {
    var button = $(event.relatedTarget) // Button that triggered the modal
        // Extract info from data-* attributes
    var nombre = button.data("nombre")
    var id = button.data("id");
    var pegi = button.data("pegi");
    var desc = button.data("desc");
    var dist = document.getElementById("dist" + id).innerHTML;
    var plat = document.getElementById("plat" + id).innerHTML;
    var desa = document.getElementById("desa" + id).innerHTML;

    //data-plat=@{${juego.plat.id}},
    var anio = button.data("anio");

    // If necessary, you could initiate an AJAX request here (and then do the
    // updating in a callback).
    // Update the modal's content. We'll use jQuery here, but you could use a
    // data binding library or other methods instead.
    var modal = $(this)
    modal.find(".modal-title").text("Editar Juego: " + nombre)
    modal.find(".modal-body #ID").val(id);
    modal.find(".modal-body #nombre").val(nombre);
    modal.find(".modal-body #pegi").val(pegi);
    modal.find(".modal-body #anio").val(anio);
    modal.find(".modal-body #desc").val(desc);

    modal.find(".modal-body #" + plat).attr("checked", "checked");
    modal.find(".modal-body #" + dist).attr("checked", "checked");
    modal.find(".modal-body #" + desa).attr("checked", "checked");


});


//BORRAR JUEGO
$("#borrarJuego").on("show.bs.modal", function(event) {
    var button = $(event.relatedTarget) // Button that triggered the modal
        // Extract info from data-* attributes
    var nombre = button.data("nombre")
    var id = button.data("id");


    var modal = $(this)
    modal.find(".modal-title").text("¿Quieres borrar el Juego: " + nombre + "?")
    modal.find(".modal-footer #botonBorrar").attr("href", "/Wibby/juegos/borrar/" + id);



});

//BORRAR PLATAFORMA
$("#borrarPlataforma").on("show.bs.modal", function(event) {
    var button = $(event.relatedTarget) // Button that triggered the modal
        // Extract info from data-* attributes
    var nombre = button.data("nombre")
    var id = button.data("id");
    var modal = $(this)
    modal.find(".modal-title").text("¿Quieres borrar la Plataforma: " + nombre + "?")
    modal.find(".modal-footer #botonBorrar").attr("href", "/Wibby/plataformas/borrar/" + id);
});


//BORRAR DESARROLLADOR
$("#borrarDesarrollador").on("show.bs.modal", function(event) {
    var button = $(event.relatedTarget) // Button that triggered the modal
        // Extract info from data-* attributes
    var nombre = button.data("nombre")
    var id = button.data("id");


    var modal = $(this)
    modal.find(".modal-title").text("¿Quieres borrar el Desarrollador: " + nombre + "?")
    modal.find(".modal-footer #botonBorrar").attr("href", "/Wibby/desarrolladores/borrar/" + id);
});


//BORRAR DISTRIBUIDOR
$("#borrarDistribuidor").on("show.bs.modal", function(event) {
    var button = $(event.relatedTarget) // Button that triggered the modal
        // Extract info from data-* attributes
    var nombre = button.data("nombre")
    var id = button.data("id");

    var modal = $(this)
    modal.find(".modal-title").text("¿Quieres borrar el Distribuidor: " + nombre + "?")
    modal.find(".modal-footer #botonBorrar").attr("href", "/Wibby/distribuidores/borrar/" + id);
});


// validacion del formulario crear juegos
function validateFormCrearJuegos() {

    var pegi = document.forms["crear"]["pegi"].value;
    var anio = document.forms["crear"]["anio"].value;
    var lista = document.forms["crear"].getElementsByTagName("input");


    var regpegi = /(3|7|12|16|18)/g
    var reganio = /^(19|20)\d{2}$/g
    var error = 0;

    var checked = false;
    for (var i = 0; i < lista.length; i++) {
        if (lista[i].name == "plat.id" && lista[i].checked) {
            checked = true;
        }
    }
    if (!checked) {
        document.getElementById("splat-crear").innerHTML = "Selecciona una Plataforma"
        error++
    } else {
        document.getElementById("splat-crear").innerHTML = ""

    }


    checked = false;
    for (var i = 0; i < lista.length; i++) {
        if (lista[i].name == "dist.id" && lista[i].checked) {
            checked = true;
        }
    }
    if (!checked) {
        document.getElementById("sdist-crear").innerHTML = "Selecciona un Distribuidor "
        error++
    } else {
        document.getElementById("sdist-crear").innerHTML = ""


    }

    checked = false;
    for (var i = 0; i < lista.length; i++) {
        if (lista[i].name == "desa.id" && lista[i].checked) {
            checked = true;
        }
    }
    if (!checked) {
        document.getElementById("sdesa-crear").innerHTML = "Selecciona un Desarrollador "
        error++
    } else {
        document.getElementById("sdesa-crear").innerHTML = ""


    }

    if (pegi < 19 && regpegi.test(pegi)) {
        document.getElementById("spegi-crear").innerHTML = "";
    } else {
        document.getElementById("spegi-crear").innerHTML = "Introduce un código pegi válido: 3,7,12,16 ó 18";
        error++;
    }

    if (reganio.test(anio)) {
        document.getElementById("sanio-crear").innerHTML = "";
    } else {
        document.getElementById("sanio-crear").innerHTML = "Introduce un año con formato 'YYYY', del siglo 20 o 21 ";
        error++;
    }

    if (error > 0) {
        return false
    } else {
        return true;
    }

}


//validacion formulario editar Juegos
function validateFormEditarJuegos() {


    var pegi = document.forms["editar"]["pegi"].value;
    var anio = document.forms["editar"]["anio"].value;
    var lista = document.forms["editar"].getElementsByTagName("input");


    var regpegi = /(3|7|12|16|18)/g
    var reganio = /^(19|20)\d{2}$/g
    var error = 0;

    var checked = false;
    for (var i = 0; i < lista.length; i++) {
        if (lista[i].name == "plat.id" && lista[i].checked) {
            checked = true;
        }
    }
    if (!checked) {
        document.getElementById("splat-editar").innerHTML = "Selecciona una Plataforma"
        error++
    } else {
        document.getElementById("splat-editar").innerHTML = ""

    }


    checked = false;
    for (var i = 0; i < lista.length; i++) {
        if (lista[i].name == "dist.id" && lista[i].checked) {
            checked = true;
        }
    }
    if (!checked) {
        document.getElementById("sdist-editar").innerHTML = "Selecciona un Distribuidor "
        error++
    } else {
        document.getElementById("sdist-editar").innerHTML = ""


    }


    checked = false;
    for (var i = 0; i < lista.length; i++) {
        if (lista[i].name == "desa.id" && lista[i].checked) {
            checked = true;
        }
    }
    if (!checked) {
        document.getElementById("sdesa-editar").innerHTML = "Selecciona un Desarrollador "
        error++
    } else {
        document.getElementById("sdesa-editar").innerHTML = ""


    }



    if (pegi < 19 && regpegi.test(pegi)) {
        document.getElementById("spegi-editar").innerHTML = "";
    } else {
        document.getElementById("spegi-editar").innerHTML = "Introduce un código pegi válido: 3,7,12,16 ó 18";
        error++;
    }
    if (reganio.test(anio)) {
        document.getElementById("sanio-editar").innerHTML = "";
    } else {
        document.getElementById("sanio-editar").innerHTML = "Introduce un año con formato 'YYYY', del siglo 20 o 21 ";
        error++;
    }

    if (error > 0) {
        return false
    } else {
        return true;
    }

}

// validacion del formulario crear Distribuidores
function validateFormCrear() {

    var anio = document.forms["crear"]["anio"].value;
    var id = document.forms["crear"]["ID-crear"].value;

    var regid = new RegExp("^[a-zA-Z0-9]*$");
    var reganio = /^(19|20)\d{2}$/g
    var error = 0;

    if (regid.test(id)) {
        document.getElementById("sid-crear").innerHTML = "";
    } else {
        document.getElementById("sid-crear").innerHTML = "El Id no puede contener espacios en blanco";
        error++;
    }
    if (reganio.test(anio)) {
        document.getElementById("sanio-crear").innerHTML = "";
    } else {
        document.getElementById("sanio-crear").innerHTML = "Introduce un año con formato 'YYYY', del siglo 20 o 21 ";
        error++;
    }

    if (error > 0) {
        return false
    } else {
        return true;
    }

}
//validacion formulario editar Distribuidores
function validateFormEditar() {

    var anio = document.forms["editar"]["anio"].value;

    var reganio = /^(19|20)\d{2}$/g
    var error = 0;


    if (reganio.test(anio)) {
        document.getElementById("sanio-editar").innerHTML = "";
    } else {
        document.getElementById("sanio-editar").innerHTML = "Introduce un año con formato 'YYYY', del siglo 20 o 21 ";
        error++;
    }

    if (error > 0) {
        return false
    } else {
        return true;
    }

}





//Búsqueda
function Busqueda() {
    var filtro = document.getElementById("buscar").value;
    document.getElementById("buscarForm").setAttribute("action", "/Wibby/juegos/" + filtro);
}


function imgCrear(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();

        reader.onload = function(e) {
            $("#img-tag-crear").attr("src", e.target.result);
        }
        reader.readAsDataURL(input.files[0]);
    }
}


$("#img-crear").change(function() {
    imgCrear(this);
});


function imgEditar(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();

        reader.onload = function(e) {
            $("#img-tag").attr("src", e.target.result);
        }
        reader.readAsDataURL(input.files[0]);
    }
}


$("#img").change(function() {
    imgEditar(this);
});