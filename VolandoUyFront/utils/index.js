export const asientos = ["turista",'ejecutivo'];

export const getUsuario = () =>{
    const userString = localStorage.getItem("usuario")
    if(userString){
        try {
            return JSON.parse(userString);
        } catch (e) {
            console.error(e)
            localStorage.setItem("usuario","")
        }
    }

    return null;
}

const routes = {
    '/': {
        security:()=>true,
        pantalla:"Home",
    },
    '/login':{
        security:()=>!getUsuario(),
        pantalla:"LogIn"
    },
    '/registro':{
        security:()=>!getUsuario(),
        pantalla:"Registro"
    },
    '/logout':{
        security:()=> !!getUsuario(),
        pantalla:"logout"
    },
    '/listarRutas':{
        security:()=>true,
        pantalla:"ListarRutas"
    },
    '/consultaRuta':{
        security:()=> true,
        pantalla:"ConsultaRuta"
    },
    '/consultaUsuario':{
        security:()=>getUsuario(),
        pantalla:"ConsultaUsuario"
    },
    '/reservarVuelo':{
        security:()=>{
            const usuario = getUsuario();
            return !!usuario && usuario.tipo == 'cliente'
        },
        pantalla:"ReservarVuelo"
    },
    '/comprarPaquete':{
        security:()=>{
            const usuario = getUsuario();
            return !!usuario && usuario.tipo == 'cliente'
        },
        pantalla:"ComprarPaquete"
    },
    '/altaVuelo':{
        security:()=>{
            const usuario = getUsuario();
            return !!usuario && usuario.tipo == 'aerolinea'
        },
        pantalla:"AltaVuelo"
    },
    '/listarPaquetes':{
        security:()=> true,
        pantalla:"ListarPaquetes"
    },
    '/listarUsuarios':{
        security:()=> true,
        pantalla:"ListarUsuarios"
    },
    '/consultaPaquete':{
        security:()=> true,
        pantalla:"ConsultaPaquete"
    },
    '/consultarUsuario':{
        security:()=> true,
        pantalla:"ConsultaUsuario"
    },
    '/altaRuta':{
        security:()=>{
            const usuario = getUsuario();
            return !!usuario && usuario.tipo === 'aerolinea'
        },
        pantalla:"AltaRutaVuelo"
    },
    '/listarVuelos':{
        security:()=>true,
        pantalla:"ListarVuelos"
    },
    '/consultaVuelo':{
        security:()=>true,
        pantalla:"ConsultaVuelo"
    },
    '/listarReservas':{
        security:()=> !!getUsuario(),
        pantalla:"ListarReservas"
    },
    '/consultaReserva':{
        security:()=> !!getUsuario(),
        pantalla:"ConsultaReserva"
    }
};

export const navigate = async (path,params={}) => {
    const route = routes[path];
    if(!route || !route.security()){
        navigate("/");
        return;
    }

    const pantalla = await fetch(`./pantallas/${route.pantalla}/${route.pantalla}.html`);
    const html = await pantalla.text();
    localStorage.setItem("params",JSON.stringify(params))
    $('#app').html(html);
    if(localStorage.getItem("lastRoute") != path)
        localStorage.setItem("filtros","{}");
    localStorage.setItem("lastRoute",path);
    $('#side-bar').attr("update","jeje"); //NO BORRAR, ESTO ES IMPORTANTE
}