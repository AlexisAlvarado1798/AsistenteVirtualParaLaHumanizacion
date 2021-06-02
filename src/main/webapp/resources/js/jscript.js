/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function invalidarBtnAtras(){
    window.location.hash = "no-back-buttom";
    window.location.hash = "Again-No-back-buttom"; //chrome
    window.onhashchange = function (){
        window.location.hash = "no-back-buttom";
    };
}

PrimeFaces.locales['es']
 = {
     closeText: 'Cerrar',
     prevText: 'Anterior',
     nextText: 'Siguiente',
     monthNames: ['Enero','Febrero','Marzo','Abril','Mayo','Junio','Julio','Agosto','Septiembre','Octubre','Noviembre','Diciembre'],
     monthNamesShort: ['Ene','Feb','Mar','Abr','May','Jun','Jul','Ago','Sep','Oct','Nov','Dic'], 
     dayNames: ['Domingo','Lunes','Martes','Miercoles','Jueves','Viernes','Sabado'],
     dayNamesShort: ['Dom','Lun','Mar','Mie','Jue','Vie','Sab'],
     dayNamesMin: ['D','L','M','M','J','V','S'],
     weekHeader: 'Semana',
     FirstDay: 1,
     isRTL: false,
     ShowMonthAfterYear: false,
     yearSuffix: '',
     timeOnlyTitle: 'Tiempo',
     timeText: 'Tiempo',
     hourText: 'Hora',
     minuteText: 'Minutos',
     secondText: 'Segundos',
     currentText: 'Fecha Actual',
     ampm: false,
     month: 'Mes',
     week: 'Semana',
     day: 'Dia',
     allDayText: 'Todo el dia'     
 };