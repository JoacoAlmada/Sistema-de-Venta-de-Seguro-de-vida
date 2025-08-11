import {Estado} from './enums';

export interface AltaDTO {
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
