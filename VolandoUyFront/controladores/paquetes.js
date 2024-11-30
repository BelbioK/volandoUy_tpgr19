export default class ControladorPaquete{
    static async consultarPaquete(nombre){
        return (await this.listarPaquetes()).find(p => p.nombre == nombre) || null
    }
    static async listarPaquetes(){
        return [ 
            {
                nombre: "Cruzar el Charco",
                imagen:"PQ2.jpeg",
                descripcion: "Escapate a Buenos Aires y sumergete en la vibrante vida de la capital argentina. Este paquete incluye vuelos directos",
                periodoDeValidez: 150,
                descuento: 30,
                costoAsociado: 1008, 
                comprado: true,
                fechaAlta:"25/08/2024",
                rutas:[
                    {
                        aerolinea:"aerolineas",
                        nombre:"AR1380",
                        descripcionCorta:"Buenos Aires - Montevideo por Aerolineas Argentinas (AR1380)",
                        imagen:"RV03.jpg",
                        fechaAlta:"09/08/2024",
                        cantidad:2,
                        tipoAsiento:"turista"
                    },
                    {
                        aerolinea:"aerolineas",
                        nombre:"AR1381",
                        descripcionCorta:"Montevideo - Buenos Aires por Aerolineas Argentinas (AR1381)",
                        imagen:"RV04.jpg",
                        fechaAlta:"09/08/2024",
                        cantidad:3,
                        tipoAsiento:"ejecutivo"
                    }
                ],
                compras:[
                    {
                        cliente:{
                            tipo: "cliente",
                            nickname: "ejstar",
                            nombre: "Emily",
                            apellido: "Jhonson",
                            imagen: "US07.jpeg",
                        },
                        fechaCompra:"26/08/2024",
                        fechaValidez:"23/01/2025",
                        costo:1008
                    },
                    { // TODO: eliminar esta compra
                        cliente:{
                            tipo: "cliente",
                            nickname: "pedroxr",
                            nombre: "Pedron",
                            apellido: "Josefa",
                            imagen: "US07.jpeg",
                        },
                        fechaCompra:"26/08/2024",
                        fechaValidez:"23/01/2025",
                        costo:1008
                    }
                ]
            },
        ]
    }
    static async listarPaquetesAplicables(reserva){
        console.log(reserva)
        return [
            {
                nombre: "Cruzar el Charco",
                imagen:"PQ2.jpeg",
                cantidadRestante:4,
                descuento:30
            }
        ]
    }

    static async comprarPaquete(paquete){
        console.log("comprar paquete :D",paquete)
    }
}

