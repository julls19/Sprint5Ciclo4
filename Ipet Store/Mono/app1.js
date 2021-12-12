const contenido = document.querySelector(".contenido");
const linkProductos = document.querySelector("#link-productos");

const modulos = document.querySelectorAll("[id^='modulo-']");
modulos.forEach(modulo=>{
    modulo.classList.add("d-none");
})

document.querySelector("#modulo-inicio").classList.remove("d-none");

const enlaces = document.querySelectorAll("[id^='link-']");
console.log(enlaces)
enlaces.forEach(enlace=>{
    enlace.addEventListener("click", (e)=>{
        e.preventDefault();
        let link_id = enlace.getAttribute("id");
        let lista = link_id.split("-");
        let m = lista[1];

        modulos.forEach(modulo=>{
            modulo.classList.add("d-none");
        })

        document.querySelector("#modulo-"+m).classList.remove("d-none");

    })
})

linkProductos.addEventListener("click", (e)=>{
    e.preventDefault();
    //consumir el api

    fetch("http://localhost:8080/ApiRestG22/api/v1/productos", {
        headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTYzNjg0NzIyMywiZXhwIjoxNjM2ODQ4MTIzLCJ1c3VhcmlvIjoiYWRtaW4iLCJ0aXBvIjoxfQ.hJ2KSP_lHEUvpy3f8nOfLTdPV9mUKnwGEysD0T56Ozg'
        }
    })
    .then(respuesta=>respuesta.json())
    .then(datos=>{
        console.log(datos)
        datos = datos.datos;
        for(let i=0;i<datos.length;i++){
            contenido.innerHTML = contenido.innerHTML + `
            <div class="col-md-3 mb-5">
                <div class="card">
                    <div class="card-header bg-dark text-center text-white">
                        <h3>${datos[i].nombre}</h3>
                    </div>
                    <div class="card-body">
                        <p>
                            ${datos[i].precio}
                            - 
                            <small>${datos[i].cantidad}</small>
                        </p>
                    </div>
                    <div class="card-footer">

                    </div>
                </div>
            </div>    
            `;
        }
        
    })
    .catch(error=>{
        console.log("Error: "+error)
    })
})