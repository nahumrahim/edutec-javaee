import { IRol } from "../roles/interface";

export interface IUsuario { 
	id: number
	codigoLdap: string
	nombre: string
	email: string
	telefono: string
	activo: number
	esLdap: number
	claveLocal: string
	rol: IRol
}