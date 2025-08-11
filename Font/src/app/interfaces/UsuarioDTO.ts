import {Estado, Genero} from './enums';

export interface UsuarioDTO {
  id_usuario: number,
  identificacion: string,
  nombre_completo: string,
  contrasenia: string,
  edad: number,
  seguro: string,
  email : string,
  telefono : string,
  genero: string,
  estado: string,
  maneja: boolean,
  usa_lentes: boolean,
  diabetico: boolean,
  otra_enfermedad: boolean,
  cual_enfermedad: string;
}

export interface UsuarioFiltro {
  genero? : string,
  estado?: string,
  seguro?: string,
  usa_lentes?: boolean,
  maneja? : boolean,
  diabetico? : boolean,
  otra_enfermedad? : boolean;
}
