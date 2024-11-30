import { filterObject } from "../utils/helpers.js";

const usuarios = {
    aerolineas: {
    tipo: "aerolinea",
    nickname: "aerolineas",
    nombre: "Aerolineas Argentinas",
    correo: "servicioalcliente@aerolineas.com.uy",
    contrasenia: "zaq1xsw2",
    imagen: "US01.png",
    fechaAlta: "2024-01-01",
    descripcion:
      "Aerolinea nacional de Argentina que ofrece vuelosdirectos entre multiples destinos.",
    sitioWeb: "https://www.aerolineas.com.ar",
  },
  ejstar:{
    tipo: "cliente",
    nickname: "ejstar",
    nombre: "Emily",
    apellido: "Jhonson",
    fechaNacimiento: "1985-06-24",
    nacionalidad: "Estadounidense",
    tipoDoc: "pasaporte",
    numeroDoc: "485719842",
    correo: "emily.h@hotmail.com",
    contrasenia: "lkjoiu987",
    imagen: "US07.jpeg",
    fechaAlta: "2024-01-01",
  },
  // TODO: Eliminar estos usuario de prueba
  pedroxr:{tipo:"cliente",nickname:"pedroxr",correo:"pedronga@gmail.com", contrasenia:"123456", nacionalidad:"Uruguaya", nombre:"Pedro",apellido:"Pedrosa", tipoDoc:"Cedula", numeroDoc:"22222222", fechaAlta: "2024-01-01", fechaNacimiento:"1985-06-24", imagen:"US11.jpg"},
  boeing:{tipo:"aerolinea",nickname:"boeing", correo:"boeing@gmail.com", contrasenia:"123456",nombre:"Boeing",descripcion:"Aerolinea internacional de lujo con destinos a todo el mundo.",sitioWeb:"https://www.starlines.com",imagen:"US10.jpg"}
};

export default class ControladorUsuario {
  static async login(username, password) {
    let usuario = "";
    switch (username) {
      case "cliente":
        usuario = filterObject(usuarios.pedroxr, (v, k)=> ["tipo", "nickname", "nombre", "apellido", "imagen"].includes(k))
        break;
      case "aerolinea":
        usuario = filterObject(usuarios.aerolineas, (v, k)=> ["tipo", "nickname", "nombre", "imagen"].includes(k))
        break;
    }
    localStorage.setItem("usuario", JSON.stringify(usuario));
    location.reload();
  }

    static async logout(){
        localStorage.setItem("usuario","");
        location.reload();
    }
    
    static async loginUsuario(usuario, contrasenia){
      if(usuario.includes("@")){
        const persona = await this.consultarUsuarioEmail(usuario)
        if(!persona) return false
        if(contrasenia == persona.contrasenia){
          localStorage.setItem("usuario", JSON.stringify(persona));
          location.reload();
          return true
        }
        return false
      }
      const persona = await this.consultarUsuarios(usuario)
      if(!persona) return false
        if(contrasenia == persona.contrasenia){
          localStorage.setItem("usuario", JSON.stringify(persona));
          location.reload();
          return true
        }
      return false
    }
    
    static async consultarUsuarioEmail(email){
      switch(email){
          case 'pedronga@gmail.com': return {
            tipo:"cliente",
            nickname:"pedroxr", 
            contrasenia:"123456", 
            nacionalidad:"Uruguaya", 
            email:"pedronga@gmail.com", 
            nombre:"Pedro",
            apellido:"Pedrosa", 
            tipoDocumento:"Cedula", 
            numeroDocumento:"22222222", 
            fechaAlta: "01/01/2024", 
            fechaNacimiento:"2024-01-01", 
            imagen:"US11.jpg"
          }

          case 'emily.h@hotmail.com' : return {
            tipo: "cliente",
            nickname: "ejstar",
            nombre: "Emily",
            apellido: "Jhonson",
            fechaNacimiento: "24/06/1985",
            nacionalidad: "Estadounidense",
            tipoDoc: "pasaporte",
            numeroDoc: "485719842",
            correo: "emily.h@hotmail.com",
            contrasenia: "lkjoiu987",
            imagen: "US07.jpeg",
            fechaAlta: "01/01/2024",
          }

          case 'servicioalcliente@aerolineas.com.uy' : return {
            tipo: "aerolinea",
            nickname: "aerolineas",
            nombre: "Aerolineas Argentinas",
            correo: "servicioalcliente@aerolineas.com.uy",
            contrasenia: "zaq1xsw2",
            imagen: "US01.png",
            fechaAlta: "01/01/2024",
            descripcion:
              "Aerolinea nacional de Argentina que ofrece vuelosdirectos entre multiples destinos.",
            sitioWeb: "https://www.aerolineas.com.ar",
          }

          case 'boeing@gmail.com': return {
            tipo:"aerolinea",
            nickname:"boeing", 
            email:"boeing@gmail.com", 
            nombre:"Boeing",
            descripcion:"Aerolinea internacional de lujo con destinos a todo el mundo.",
            link:"https://www.starlines.com",
            imagen:"US10.jpg",
            contrasenia: "123456"
          }  

          default: return (await this.listarUsuarios()).find(u => u.email == email) || null
        }
  }
    static async consultarUsuarios(nickname){
        switch(nickname){
          case 'pedroxr': return {
            tipo:"cliente",
            nickname:"pedroxr", 
            contrasenia:"123456", 
            nacionalidad:"Uruguaya", 
            correo:"pedronga@gmail.com", 
            nombre:"Pedro",
            apellido:"Pedrosa", 
            tipoDoc:"Cedula", 
            numeroDoc:"22222222", 
            fechaAlta: "2024-01-01", 
            fechaNacimiento:"2001-09-11", 
            imagen:"US11.jpg"
          }

          case 'ejstar' : return {
            tipo: "cliente",
            nickname: "ejstar",
            nombre: "Emily",
            apellido: "Jhonson",
            fechaNacimiento: "1985-06-24",
            nacionalidad: "Estadounidense",
            tipoDoc: "pasaporte",
            numeroDoc: "485719842",
            correo: "emily.h@hotmail.com",
            contrasenia: "lkjoiu987",
            imagen: "US07.jpeg",
            fechaAlta: "2024-01-01",
          }

          case 'aerolineas' : return {
            tipo: "aerolinea",
            nickname: "aerolineas",
            nombre: "Aerolineas Argentinas",
            correo: "servicioalcliente@aerolineas.com.uy",
            contrasenia: "zaq1xsw2",
            imagen: "US01.png",
            fechaAlta: "01/01/2024",
            descripcion:
              "Aerolinea nacional de Argentina que ofrece vuelosdirectos entre multiples destinos.",
            sitioWeb: "https://www.aerolineas.com.ar",
          }

          case 'boeing': return {
            tipo:"aerolinea",
            nickname:"boeing", 
            correo:"boeing@gmail.com", 
            nombre:"Boeing",
            descripcion:"Aerolinea internacional de lujo con destinos a todo el mundo.",
            sitioWeb:"https://www.starlines.com",
            imagen:"US10.jpg",
            contrasenia: "123456",
            fechaAlta: "2024-01-01",
          }  

            default: return (await this.listarUsuarios()).find(u => u.nickname == nickname) || null
          }
    }

    static async listarReservasCliente(nickname){
      switch(nickname){
        case 'pedroxr': return [
          {
            ref:'General', 
            aerolinea:"aerolineas", 
            ruta:"AR1380", 
            vuelo:"AR1380939", 
            tipoAsiento:"Ejecutivo", 
            cantidadPasajeros:"8", 
            cantidadEquipajeExtra:5, 
            fechaReserva:"28/08/2024", 
            costo:2870
          },
        ]
      }
    }


  static async listarUsuarios() {
    return [
      {
        tipo: "aerolinea",
        nickname: "aerolineas",
        nombre: "Aerolineas Argentinas",
        correo: "servicioalcliente@aerolineas.com.uy",
        contrasenia: "zaq1xsw2",
        imagen: "US01.png",
        fechaAlta: "01/01/2024",
        descripcion:
          "Aerolinea nacional de Argentina que ofrece vuelosdirectos entre multiples destinos.",
        sitioWeb: "https://www.aerolineas.com.ar",
      },
      {
        tipo: "cliente",
        nickname: "ejstar",
        nombre: "Emily",
        apellido: "Jhonson",
        fechaNacimiento: "24/06/1985",
        nacionalidad: "Estadounidense",
        tipoDoc: "pasaporte",
        numeroDoc: "485719842",
        correo: "emily.h@hotmail.com",
        contrasenia: "lkjoiu987",
        imagen: "US07.jpeg",
        fechaAlta: "01/01/2024",
      },
      {
        tipo:"aerolinea",
        nickname:"boeing", 
        correo:"boeing@gmail.com", 
        nombre:"Boeing",
        descripcion:"Aerolinea internacional de lujo con destinos a todo el mundo.",
        link:"https://www.starlines.com",
        imagen:"US10.jpg",
        contrasenia: "123456",
        fechaAlta: "01/01/2024",
      },
      {
        tipo:"cliente",
        nickname:"pedroxr", 
        contrasenia:"123456", 
        nacionalidad:"Uruguaya", 
        correo:"pedronga@gmail.com", 
        nombre:"Pedro",
        apellido:"Pedrosa", 
        tipoDoc:"Cedula", 
        numeroDoc:"22222222", 
        fechaAlta: "01/01/2024", 
        fechaNacimiento:"2024-01-01", 
        imagen:"US11.jpg"
      }
    ];
  }

  static async listarClientes(){
    return [
      {
        tipo: "cliente",
        nickname: "ejstar",
        nombre: "Emily",
        apellido: "Jhonson",
        fechaNacimiento: "24/06/1985",
        nacionalidad: "Estadounidense",
        tipoDoc: "pasaporte",
        numeroDoc: "485719842",
        correo: "emily.h@hotmail.com",
        contrasenia: "lkjoiu987",
        imagen: "US07.jpeg",
        fechaAlta: "01/01/2024",
      },
      {
        tipo:"cliente",
        nickname:"pedroxr", 
        contrasenia:"123456", 
        nacionalidad:"Uruguaya", 
        correo:"pedronga@gmail.com", 
        nombre:"Pedro",
        apellido:"Pedrosa", 
        tipoDoc:"Cedula", 
        numeroDoc:"22222222", 
        fechaAlta: "01/01/2024", 
        fechaNacimiento:"2024-01-01", 
        imagen:"US11.jpg"
      }
    ]
  }

  static async listarAerolineas() {
    return [
      {
        tipo: "aerolinea",
        nickname: "aerolineas",
        nombre: "Aerolineas Argentinas",
        correo: "servicioalcliente@aerolineas.com.uy",
        contrasenia: "zaq1xsw2",
        imagen: "US01.png",
        fechaAlta: "01/01/2024",
        descripcion:
          "Aerolinea nacional de Argentina que ofrece vuelosdirectos entre multiples destinos.",
        sitioWeb: "https://www.aerolineas.com.ar",
      },
      {
        tipo:"aerolinea",
        nickname:"boeing", 
        email:"boeing@gmail.com", 
        nombre:"Boeing",
        descripcion:"Aerolinea internacional de lujo con destinos a todo el mundo.",
        link:"https://www.starlines.com",
        imagen:"US10.jpg",
        contrasenia: "123456",
        fechaAlta: "01/01/2024",
      }
    ];
  }

  static async altaUsuario(usuario){
    
  }
}
