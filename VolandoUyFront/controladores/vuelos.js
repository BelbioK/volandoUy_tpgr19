export default class ControladorVuelo{
    static async consultarVuelo(nombre){
        return {}
  }

    static async listarCategorias(){
      return ["America","Temporada","Cortos"];
    }

    static async listarCiudades(){
      return [
        {
          nombre:"Montevideo",
          pais:"Uruguay",
          aeropuerto:"Carrasco",
          descripcion:"Capital uruguaya, conocida por su Rambla, arquitectura colonial y vibrante vida cultural.",
          sitioWeb:"https://montevideo.gub.uy",
          fechaAlta:"01/04/2024"
        },
        {
          nombre:"Buenos Aires",
          pais:"Argentina",
          aeropuerto:"Aeroparque Jorge Newbery",
          descripcion:"Vibrante capital argentina, conocida por su arquitectura, tango y vida cultural.",
          sitioWeb:"https://turismo.buenosaires.gob.ar/es",
          fechaAlta:"05/07/2024"
        }
      ]
    }
    static async listarRutasDeVuelo(){
      return [
        {
          aerolinea:"aerolineas",
          nombre:"AR1380",
          descripcion:"Tiempo de vuelo 1 hora, driecto y sin escalas",
          hora:"07:55",
          costoTurista:120,
          costoEjecutivo:340,
          costoEquipaje:30,
          origen:{
            nombre:"Buenos Aires",
            pais:"Argentina",
            aeropuerto:"Aeroparque Jorge Newbery",
            descripcion:"Vibrante capital argentina, conocida por su arquitectura, tango y vida cultural.",
            sitioWeb:"https://turismo.buenosaires.gob.ar/es",
            fechaAlta:"05/07/2024"
          },
          destino: {
            nombre:"Montevideo",
            pais:"Uruguay",
            aeropuerto:"Carrasco",
            descripcion:"Capital uruguaya, conocida por su Rambla, arquitectura colonial y vibrante vida cultural.",
            sitioWeb:"https://montevideo.gub.uy",
            fechaAlta:"01/04/2024"
          },
          categorias:["America","Temporada"],
          descripcionCorta:"Buenos Aires - Montevideo por Aerolineas Argentinas (AR1380)",
          estado:"confirmada",
          imagen:"RV03.jpg",
          fechaAlta:"09/08/2024"
        },
        {
          aerolinea:"aerolineas",
          nombre:"AR1381",
          descripcion:"Tiempo estimado de vuelo 55 minutos",
          hora:"09:35",
          costoTurista:160,
          costoEjecutivo:400,
          costoEquipaje:30,
          origen: {
            nombre:"Montevideo",
            pais:"Uruguay",
            aeropuerto:"Carrasco",
            descripcion:"Capital uruguaya, conocida por su Rambla, arquitectura colonial y vibrante vida cultural.",
            sitioWeb:"https://montevideo.gub.uy",
            fechaAlta:"01/04/2024"
          },
          destino:{
            nombre:"Buenos Aires",
            pais:"Argentina",
            aeropuerto:"Aeroparque Jorge Newbery",
            descripcion:"Vibrante capital argentina, conocida por su arquitectura, tango y vida cultural.",
            sitioWeb:"https://turismo.buenosaires.gob.ar/es",
            fechaAlta:"05/07/2024"
          },
          categorias:["Cortos","America"],
          descripcionCorta:"Montevideo - Buenos Aires por Aerolineas Argentinas (AR1381)",
          estado:"confirmada",
          imagen:"RV04.jpg",
          fechaAlta:"09/08/2024"
        }
      ]
    }
    static async listarRutasConfirmadas(){
      let rutas = await this.listarRutasDeVuelo();
      rutas = rutas.filter(r=> r.estado == "confirmada")
      return rutas
    }
     
    static async consultarRuta(nombre){
      return (await this.listarRutasDeVuelo()).find(r => r.nombre == nombre);
    }
    static async listarRutasDeAerolinea(aerolinea){
        const rutasPorAero = {
          "aerolineas":[
            {
              aerolinea:"aerolineas",
              nombre:"AR1380",
              descripcion:"Tiempo de vuelo 1 hora, driecto y sin escalas",
              hora:"07:55",
              costoTurista:120,
              costoEjecutivo:340,
              costoEquipaje:30,
              origen:{
                nombre:"Buenos Aires",
                pais:"Argentina",
                aeropuerto:"Aeroparque Jorge Newbery",
                descripcion:"Vibrante capital argentina, conocida por su arquitectura, tango y vida cultural.",
                sitioWeb:"https://turismo.buenosaires.gob.ar/es",
                fechaAlta:"05/07/2024"
              },
              destino: {
                nombre:"Montevideo",
                pais:"Uruguay",
                aeropuerto:"Carrasco",
                descripcion:"Capital uruguaya, conocida por su Rambla, arquitectura colonial y vibrante vida cultural.",
                sitioWeb:"https://montevideo.gub.uy",
                fechaAlta:"01/04/2024"
              },
              categorias:["America","Temporada"],
              descripcionCorta:"Buenos Aires - Montevideo por Aerolineas Argentinas (AR1380)",
              estado:"confirmada",
              imagen:"RV03.jpg",
              fechaAlta:"09/08/2024"
            },
            {
              aerolinea:"aerolineas",
              nombre:"AR1381",
              descripcion:"Tiempo estimado de vuelo 55 minutos",
              hora:"09:35",
              costoTurista:160,
              costoEjecutivo:400,
              costoEquipaje:30,
              origen: {
                nombre:"Montevideo",
                pais:"Uruguay",
                aeropuerto:"Carrasco",
                descripcion:"Capital uruguaya, conocida por su Rambla, arquitectura colonial y vibrante vida cultural.",
                sitioWeb:"https://montevideo.gub.uy",
                fechaAlta:"01/04/2024"
              },
              destino:{
                nombre:"Buenos Aires",
                pais:"Argentina",
                aeropuerto:"Aeroparque Jorge Newbery",
                descripcion:"Vibrante capital argentina, conocida por su arquitectura, tango y vida cultural.",
                sitioWeb:"https://turismo.buenosaires.gob.ar/es",
                fechaAlta:"05/07/2024"
              },
              categorias:["Cortos","America"],
              descripcionCorta:"Montevideo - Buenos Aires por Aerolineas Argentinas (AR1381)",
              estado:"confirmada",
              imagen:"RV04.jpg",
              fechaAlta:"09/08/2024"
            }
          ]
        }
        return rutasPorAero[aerolinea] || [];
    }
    static async consultarVuelo(nombre){
      return (await this.listarVuelos()).find(v => v.nombre == nombre);
    }
    static async listarVuelos(){
      return [
        {
          aerolinea:"aerolineas",
          ruta:"AR1380",
          nombre:"AR1380939",
          fecha:"24/11/2024",
          horasDur:0,
          minutosDur:26,
          asientosMaxTurista:153,
          asientosMaxEjecutivo:16,
          asientosRestTurista:0,
          asientosRestEjecutivo:0,
          fechaAlta:"26/08/2024",
          imagen:"VU08.jpg"
        },
        {
          aerolinea:"aerolineas",
          ruta:"AR1380",
          nombre:"AR13801059",
          fecha:"30/11/2024",
          horasDur:0,
          minutosDur:30,
          asientosMaxTurista:162,
          asientosMaxEjecutivo:8,
          asientosRestTurista:0,
          asientosRestEjecutivo:0,
          fechaAlta:"27/08/2024",
          imagen:"VU09.jpg"
        }
      ]
    }

    static async listarVuelosDeRuta(ruta){
        const vuelosPorRuta = {
          AR1380:[
            {
              aerolinea:"aerolineas",
              ruta:"AR1380",
              nombre:"AR1380939",
              fecha:"24/11/2024",
              horasDur:0,
              minutosDur:26,
              asientosMaxTurista:153,
              asientosMaxEjecutivo:16,
              asientosRestTurista:0,
              asientosRestEjecutivo:0,
              fechaAlta:"26/08/2024",
              imagen:"VU08.jpg"
            },
            {
              aerolinea:"aerolineas",
              ruta:"AR1380",
              nombre:"AR13801059",
              fecha:"30/11/2024",
              horasDur:0,
              minutosDur:30,
              asientosMaxTurista:162,
              asientosMaxEjecutivo:8,
              asientosRestTurista:0,
              asientosRestEjecutivo:0,
              fechaAlta:"27/08/2024",
              imagen:"VU09.jpg"
            }
          ]
        }
        return vuelosPorRuta[ruta] || [];
    }

    static async consultarReservaVuelo(nombreVuelo){
      const reservas = await this.listarReservas();
      return reservas.filter(v => v.vuelo.nombre === nombreVuelo);
    }
    static async consultarReservaVueloCliente(nombreVuelo,cliente){
      const reservas = await this.listarReservasCliente();
      return reservas.find(v => v.vuelo.nombre == nombreVuelo && v.cliente.nickname == cliente);
    }

    static async listarReservas(){
      return[
        {
          id:1,
          tipo:'general',
          aerolinea:{
            nickname: "aerolineas",
            nombre: "Aerolineas Argentinas",
            imagen: "US01.png",
          },
          ruta:{nombre:"AR1380", imagen:"RV03.jpg",hora:"07:55"},
          vuelo:{nombre:"AR13801059", imagen:"VU09.jpg",fecha:"30/11/2024"},
          cliente:{
              tipo: "cliente",
              nickname: "ejstar",
              nombre: "Emily",
              apellido: "Jhonson",
              imagen: "US07.jpeg",
          },
          paquete:null,
          tipoAsiento:"ejecutivo",
          equipajeExtra:5,
          fechaReserva:"28/08/2024",
          costo:2870,
          pasajes:[
            {nombre:"Emily",apellido:"Johnson"},
            {nombre:"Jack",apellido:"Johnson"},
            {nombre:"Liberty",apellido:"Trent"},
            {nombre:"Marc",apellido:"Ruffalo"},
            {nombre:"Jessica",apellido:"Landon"},
            {nombre:"Robert",apellido:"Shank"},
            {nombre:"Frank",apellido:"Trent"},
            {nombre:"Lucy",apellido:"Felton"},
          ]
        }
      ]
    }
    static async listarReservasAerolinea(aerolinea){
      return[
        {
          id:1,
          tipo:'general',
          aerolinea:{
            nickname: "aerolineas",
            nombre: "Aerolineas Argentinas",
            imagen: "US01.png",
          },          ruta:{nombre:"AR1380", imagen:"RV03.jpg",hora:"07:55"},
          vuelo:{nombre:"AR13801059", imagen:"VU09.jpg",fecha:"30/11/2024"},
          cliente:{
              tipo: "cliente",
              nickname: "ejstar",
              nombre: "Emily",
              apellido: "Jhonson",
              imagen: "US07.jpeg",
          },
          tipoAsiento:"ejecutivo",
          equipajeExtra:5,
          paquete:null,
          fechaReserva:"28/08/2024",
          costo:2870,
          pasajes:[
            {nombre:"Emily",apellido:"Johnson"},
            {nombre:"Jack",apellido:"Johnson"},
            {nombre:"Liberty",apellido:"Trent"},
            {nombre:"Marc",apellido:"Ruffalo"},
            {nombre:"Jessica",apellido:"Landon"},
            {nombre:"Robert",apellido:"Shank"},
            {nombre:"Frank",apellido:"Trent"},
            {nombre:"Lucy",apellido:"Felton"},
          ]
        }
      ]
    }
    static async listarReservasCliente(cliente){
      return[
        {
          id:1,
          tipo:'general',
          aerolinea:{
            nickname: "aerolineas",
            nombre: "Aerolineas Argentinas",
            imagen: "US01.png",
          },          ruta:{nombre:"AR1380", imagen:"RV03.jpg",hora:"07:55"},
          vuelo:{nombre:"AR13801059", imagen:"VU09.jpg",fecha:"30/11/2024"},
          cliente:{
              tipo: "cliente",
              nickname: "ejstar",
              nombre: "Emily",
              apellido: "Jhonson",
              imagen: "US07.jpeg",
          },
          paquete:null,
          tipoAsiento:"ejecutivo",
          equipajeExtra:5,
          fechaReserva:"28/08/2024",
          costo:2870,
          pasajes:[
            {nombre:"Emily",apellido:"Johnson"},
            {nombre:"Jack",apellido:"Johnson"},
            {nombre:"Liberty",apellido:"Trent"},
            {nombre:"Marc",apellido:"Ruffalo"},
            {nombre:"Jessica",apellido:"Landon"},
            {nombre:"Robert",apellido:"Shank"},
            {nombre:"Frank",apellido:"Trent"},
            {nombre:"Lucy",apellido:"Felton"},
          ]
        }
      ]
    }

  static async altaReserva(cliente, reserva){
    return new Promise((resolve)=> setTimeout(resolve(),0));
  }

    static async altaVuelo(aerolinea, vuelo){
      return new Promise((resolve)=> setTimeout(resolve(),0));
	}
    static async altaRutaDeVuelo(aerolinea, nombre, descripcion, hora, costoTurista, costoEjecutivo, costoEquipajeExtra, ciudadOrigen, ciudadDestino, categorias, imagen){
      // write an example promise console.log
      return new Promise((resolve)=> setTimeout(()=>{
        console.log(`AltaRutaDeVuelo: la ruta ${nombre} se añadio correctamente a las rutas de ${aerolinea}`);
        resolve();
      },0));
    }
}