/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function () {
    'use strict';
    angular.module('app.coOccurences', [])
            .controller('CoOccurenceGraphController', ['$scope', '$http', function ($scope, $http) {

                    var nodesArray = [
                        {label: "Adyghe", id: 0, color: "#faddd2", textcolor: "#ba9d92", size: 5, desc: "Language: <a href='http://en.wikipedia.org/wiki/Adyghe_language' target='_blank'>Adyghe</a><br/><br/>No similar languages found."},
                        {label: "Afrikaans", id: 1, color: "#e1dbae", textcolor: "#a19b6e", size: 5, desc: "Language: <a href='http://en.wikipedia.org/wiki/Afrikaans_language' target='_blank'>Afrikaans</a><br/><br/>Similar to:<ul><li><a href='http://en.wikipedia.org/wiki/Dutch_language' target='_blank'>Dutch</a> at 69%</li><li><a href='http://en.wikipedia.org/wiki/German_language' target='_blank'>German</a> at 45%</li><li><a href='http://en.wikipedia.org/wiki/West_Frisian_language' target='_blank'>West Frisian</a> at 45%</li><li><a href='http://en.wikipedia.org/wiki/Swedish_language' target='_blank'>Swedish</a> at 41%</li><li><a href='http://en.wikipedia.org/wiki/Limburgish_language' target='_blank'>Limburgish</a> at 39%</li><li><a href='http://en.wikipedia.org/wiki/Danish_language' target='_blank'>Danish</a> at 38%</li><li><a href='http://en.wikipedia.org/wiki/Norwegian_language' target='_blank'>Norwegian</a> at 37%</li><li><a href='http://en.wikipedia.org/wiki/Luxembourgish_language' target='_blank'>Luxembourgish</a> at 37%</li><li><a href='http://en.wikipedia.org/wiki/Old_High_German_language' target='_blank'>Old High German</a> at 34%</li><li><a href='http://en.wikipedia.org/wiki/English_language' target='_blank'>English</a> at 33%</li><li><a href='http://en.wikipedia.org/wiki/Old_Saxon_language' target='_blank'>Old Saxon</a> at 33%</li></ul>"},
                        {label: "Albanian", id: 2, color: "#e8b1d1", textcolor: "#a87191", size: 5, desc: "Language: <a href='http://en.wikipedia.org/wiki/Albanian_language' target='_blank'>Albanian</a><br/><br/>Similar to:<ul><li><a href='http://en.wikipedia.org/wiki/Italian_language' target='_blank'>Italian</a> at 34%</li><li><a href='http://en.wikipedia.org/wiki/Serbo_Croatian_language' target='_blank'>Serbo Croatian</a> at 34%</li><li><a href='http://en.wikipedia.org/wiki/French_language' target='_blank'>French</a> at 33%</li></ul>"},
                        {label: "Anglo Norman", id: 3, color: "#de9f86", textcolor: "#9e5f46", size: 5, desc: "Language: <a href='http://en.wikipedia.org/wiki/Anglo_Norman_language' target='_blank'>Anglo Norman</a><br/><br/>Similar to:<ul><li><a href='http://en.wikipedia.org/wiki/Old_French_language' target='_blank'>Old French</a> at 78%</li><li><a href='http://en.wikipedia.org/wiki/French_language' target='_blank'>French</a> at 66%</li><li><a href='http://en.wikipedia.org/wiki/Middle_French_language' target='_blank'>Middle French</a> at 58%</li><li><a href='http://en.wikipedia.org/wiki/Italian_language' target='_blank'>Italian</a> at 50%</li><li><a href='http://en.wikipedia.org/wiki/Catalan_language' target='_blank'>Catalan</a> at 48%</li><li><a href='http://en.wikipedia.org/wiki/Spanish_language' target='_blank'>Spanish</a> at 47%</li><li><a href='http://en.wikipedia.org/wiki/GuernÃ©siais_language' target='_blank'>GuernÃ©siais</a> at 47%</li><li><a href='http://en.wikipedia.org/wiki/JÃ¨rriais_language' target='_blank'>JÃ¨rriais</a> at 47%</li><li><a href='http://en.wikipedia.org/wiki/Occitan_language' target='_blank'>Occitan</a> at 45%</li><li><a href='http://en.wikipedia.org/wiki/Interlingua_language' target='_blank'>Interlingua</a> at 45%</li><li><a href='http://en.wikipedia.org/wiki/Romansch_language' target='_blank'>Romansch</a> at 43%</li><li><a href='http://en.wikipedia.org/wiki/Portuguese_language' target='_blank'>Portuguese</a> at 41%</li><li><a href='http://en.wikipedia.org/wiki/Latin_language' target='_blank'>Latin</a> at 40%</li><li><a href='http://en.wikipedia.org/wiki/Galician_language' target='_blank'>Galician</a> at 39%</li><li><a href='http://en.wikipedia.org/wiki/Tarantino_language' target='_blank'>Tarantino</a> at 37%</li><li><a href='http://en.wikipedia.org/wiki/Venetian_language' target='_blank'>Venetian</a> at 36%</li><li><a href='http://en.wikipedia.org/wiki/Asturian_language' target='_blank'>Asturian</a> at 36%</li><li><a href='http://en.wikipedia.org/wiki/Romanian_language' target='_blank'>Romanian</a> at 35%</li><li><a href='http://en.wikipedia.org/wiki/Ido_language' target='_blank'>Ido</a> at 35%</li><li><a href='http://en.wikipedia.org/wiki/English_language' target='_blank'>English</a> at 35%</li><li><a href='http://en.wikipedia.org/wiki/Esperanto_language' target='_blank'>Esperanto</a> at 34%</li></ul>"},
                        {label: "Aromanian", id: 4, color: "#a0a3cb", textcolor: "#60638b", size: 5, desc: "Language: <a href='http://en.wikipedia.org/wiki/Aromanian_language' target='_blank'>Aromanian</a><br/><br/>Similar to:<ul><li><a href='http://en.wikipedia.org/wiki/Romanian_language' target='_blank'>Romanian</a> at 68%</li><li><a href='http://en.wikipedia.org/wiki/Italian_language' target='_blank'>Italian</a> at 46%</li><li><a href='http://en.wikipedia.org/wiki/Latin_language' target='_blank'>Latin</a> at 45%</li><li><a href='http://en.wikipedia.org/wiki/Spanish_language' target='_blank'>Spanish</a> at 40%</li><li><a href='http://en.wikipedia.org/wiki/Catalan_language' target='_blank'>Catalan</a> at 38%</li><li><a href='http://en.wikipedia.org/wiki/Romansch_language' target='_blank'>Romansch</a> at 37%</li><li><a href='http://en.wikipedia.org/wiki/French_language' target='_blank'>French</a> at 37%</li><li><a href='http://en.wikipedia.org/wiki/Dalmatian_language' target='_blank'>Dalmatian</a> at 36%</li><li><a href='http://en.wikipedia.org/wiki/Occitan_language' target='_blank'>Occitan</a> at 36%</li><li><a href='http://en.wikipedia.org/wiki/Portuguese_language' target='_blank'>Portuguese</a> at 36%</li><li><a href='http://en.wikipedia.org/wiki/Sicilian_language' target='_blank'>Sicilian</a> at 36%</li><li><a href='http://en.wikipedia.org/wiki/Galician_language' target='_blank'>Galician</a> at 35%</li><li><a href='http://en.wikipedia.org/wiki/Venetian_language' target='_blank'>Venetian</a> at 34%</li><li><a href='http://en.wikipedia.org/wiki/Interlingua_language' target='_blank'>Interlingua</a> at 34%</li><li><a href='http://en.wikipedia.org/wiki/Asturian_language' target='_blank'>Asturian</a> at 34%</li></ul>"},
                        {label: "Asturian", id: 5, color: "#84f18c", textcolor: "#44b14c", size: 5, desc: "Language: <a href='http://en.wikipedia.org/wiki/Asturian_language' target='_blank'>Asturian</a><br/><br/>Similar to:<ul><li><a href='http://en.wikipedia.org/wiki/Spanish_language' target='_blank'>Spanish</a> at 79%</li><li><a href='http://en.wikipedia.org/wiki/Galician_language' target='_blank'>Galician</a> at 71%</li><li><a href='http://en.wikipedia.org/wiki/Catalan_language' target='_blank'>Catalan</a> at 65%</li><li><a href='http://en.wikipedia.org/wiki/Portuguese_language' target='_blank'>Portuguese</a> at 65%</li><li><a href='http://en.wikipedia.org/wiki/Italian_language' target='_blank'>Italian</a> at 61%</li><li><a href='http://en.wikipedia.org/wiki/French_language' target='_blank'>French</a> at 52%</li><li><a href='http://en.wikipedia.org/wiki/Interlingua_language' target='_blank'>Interlingua</a> at 49%</li><li><a href='http://en.wikipedia.org/wiki/Latin_language' target='_blank'>Latin</a> at 49%</li><li><a href='http://en.wikipedia.org/wiki/Occitan_language' target='_blank'>Occitan</a> at 48%</li><li><a href='http://en.wikipedia.org/wiki/Ladino_language' target='_blank'>Ladino</a> at 47%</li><li><a href='http://en.wikipedia.org/wiki/Romanian_language' target='_blank'>Romanian</a> at 46%</li><li><a href='http://en.wikipedia.org/wiki/Venetian_language' target='_blank'>Venetian</a> at 45%</li><li><a href='http://en.wikipedia.org/wiki/Ido_language' target='_blank'>Ido</a> at 42%</li><li><a href='http://en.wikipedia.org/wiki/Esperanto_language' target='_blank'>Esperanto</a> at 42%</li><li><a href='http://en.wikipedia.org/wiki/English_language' target='_blank'>English</a> at 41%</li><li><a href='http://en.wikipedia.org/wiki/Romansch_language' target='_blank'>Romansch</a> at 41%</li><li><a href='http://en.wikipedia.org/wiki/Sicilian_language' target='_blank'>Sicilian</a> at 40%</li><li><a href='http://en.wikipedia.org/wiki/Old_French_language' target='_blank'>Old French</a> at 38%</li><li><a href='http://en.wikipedia.org/wiki/Tarantino_language' target='_blank'>Tarantino</a> at 37%</li><li><a href='http://en.wikipedia.org/wiki/Serbo_Croatian_language' target='_blank'>Serbo Croatian</a> at 36%</li><li><a href='http://en.wikipedia.org/wiki/German_language' target='_blank'>German</a> at 36%</li><li><a href='http://en.wikipedia.org/wiki/Anglo_Norman_language' target='_blank'>Anglo Norman</a> at 36%</li><li><a href='http://en.wikipedia.org/wiki/Middle_French_language' target='_blank'>Middle French</a> at 35%</li><li><a href='http://en.wikipedia.org/wiki/Dalmatian_language' target='_blank'>Dalmatian</a> at 34%</li><li><a href='http://en.wikipedia.org/wiki/Aromanian_language' target='_blank'>Aromanian</a> at 34%</li><li><a href='http://en.wikipedia.org/wiki/Swedish_language' target='_blank'>Swedish</a> at 33%</li><li><a href='http://en.wikipedia.org/wiki/GuernÃ©siais_language' target='_blank'>GuernÃ©siais</a> at 33%</li><li><a href='http://en.wikipedia.org/wiki/Russian_language' target='_blank'>Russian</a> at 33%</li><li><a href='http://en.wikipedia.org/wiki/JÃ¨rriais_language' target='_blank'>JÃ¨rriais</a> at 33%</li></ul>"},
                        {label: "Azeri", id: 6, color: "#bfe09f", textcolor: "#7fa05f", size: 5, desc: "Language: <a href='http://en.wikipedia.org/wiki/Azeri_language' target='_blank'>Azeri</a><br/><br/>Similar to:<ul><li><a href='http://en.wikipedia.org/wiki/Turkish_language' target='_blank'>Turkish</a> at 61%</li><li><a href='http://en.wikipedia.org/wiki/Crimean_Tatar_language' target='_blank'>Crimean Tatar</a> at 50%</li><li><a href='http://en.wikipedia.org/wiki/Ottoman_Turkish_language' target='_blank'>Ottoman Turkish</a> at 44%</li></ul>"},
                        {label: "Basque", id: 7, color: "#b5a5c0", textcolor: "#756580", size: 5, desc: "Language: <a href='http://en.wikipedia.org/wiki/Basque_language' target='_blank'>Basque</a><br/><br/>Similar to:<ul><li><a href='http://en.wikipedia.org/wiki/Spanish_language' target='_blank'>Spanish</a> at 34%</li><li><a href='http://en.wikipedia.org/wiki/Italian_language' target='_blank'>Italian</a> at 34%</li></ul>"},
                        {label: "Breton", id: 8, color: "#accb8f", textcolor: "#6c8b4f", size: 5, desc: "Language: <a href='http://en.wikipedia.org/wiki/Breton_language' target='_blank'>Breton</a><br/><br/>Similar to:<ul><li><a href='http://en.wikipedia.org/wiki/Cornish_language' target='_blank'>Cornish</a> at 54%</li><li><a href='http://en.wikipedia.org/wiki/Welsh_language' target='_blank'>Welsh</a> at 42%</li></ul>"},
                        {label: "Bulgarian", id: 9, color: "#a1b2b2", textcolor: "#617272", size: 5, desc: "Language: <a href='http://en.wikipedia.org/wiki/Bulgarian_language' target='_blank'>Bulgarian</a><br/><br/>Similar to:<ul><li><a href='http://en.wikipedia.org/wiki/Serbo_Croatian_language' target='_blank'>Serbo Croatian</a> at 60%</li><li><a href='http://en.wikipedia.org/wiki/Russian_language' target='_blank'>Russian</a> at 58%</li><li><a href='http://en.wikipedia.org/wiki/Macedonian_language' target='_blank'>Macedonian</a> at 55%</li><li><a href='http://en.wikipedia.org/wiki/Slovene_language' target='_blank'>Slovene</a> at 44%</li><li><a href='http://en.wikipedia.org/wiki/Ukrainian_language' target='_blank'>Ukrainian</a> at 41%</li><li><a href='http://en.wikipedia.org/wiki/Czech_language' target='_blank'>Czech</a> at 41%</li><li><a href='http://en.wikipedia.org/wiki/Polish_language' target='_blank'>Polish</a> at 40%</li><li><a href='http://en.wikipedia.org/wiki/Slovak_language' target='_blank'>Slovak</a> at 38%</li><li><a href='http://en.wikipedia.org/wiki/Italian_language' target='_blank'>Italian</a> at 35%</li><li><a href='http://en.wikipedia.org/wiki/Spanish_language' target='_blank'>Spanish</a> at 33%</li></ul>"},
                        {label: "Catalan", id: 10, color: "#b7acc7", textcolor: "#776c87", size: 5, desc: "Language: <a href='http://en.wikipedia.org/wiki/Catalan_language' target='_blank'>Catalan</a><br/><br/>Similar to:<ul><li><a href='http://en.wikipedia.org/wiki/Spanish_language' target='_blank'>Spanish</a> at 75%</li><li><a href='http://en.wikipedia.org/wiki/Italian_language' target='_blank'>Italian</a> at 67%</li><li><a href='http://en.wikipedia.org/wiki/Asturian_language' target='_blank'>Asturian</a> at 65%</li><li><a href='http://en.wikipedia.org/wiki/Portuguese_language' target='_blank'>Portuguese</a> at 62%</li><li><a href='http://en.wikipedia.org/wiki/French_language' target='_blank'>French</a> at 60%</li><li><a href='http://en.wikipedia.org/wiki/Galician_language' target='_blank'>Galician</a> at 60%</li><li><a href='http://en.wikipedia.org/wiki/Occitan_language' target='_blank'>Occitan</a> at 56%</li><li><a href='http://en.wikipedia.org/wiki/Interlingua_language' target='_blank'>Interlingua</a> at 50%</li><li><a href='http://en.wikipedia.org/wiki/English_language' target='_blank'>English</a> at 50%</li><li><a href='http://en.wikipedia.org/wiki/Latin_language' target='_blank'>Latin</a> at 49%</li><li><a href='http://en.wikipedia.org/wiki/Anglo_Norman_language' target='_blank'>Anglo Norman</a> at 48%</li><li><a href='http://en.wikipedia.org/wiki/Romanian_language' target='_blank'>Romanian</a> at 45%</li><li><a href='http://en.wikipedia.org/wiki/Venetian_language' target='_blank'>Venetian</a> at 44%</li><li><a href='http://en.wikipedia.org/wiki/Ido_language' target='_blank'>Ido</a> at 44%</li><li><a href='http://en.wikipedia.org/wiki/Esperanto_language' target='_blank'>Esperanto</a> at 44%</li><li><a href='http://en.wikipedia.org/wiki/Old_French_language' target='_blank'>Old French</a> at 42%</li><li><a href='http://en.wikipedia.org/wiki/Middle_French_language' target='_blank'>Middle French</a> at 42%</li><li><a href='http://en.wikipedia.org/wiki/Romansch_language' target='_blank'>Romansch</a> at 42%</li><li><a href='http://en.wikipedia.org/wiki/Serbo_Croatian_language' target='_blank'>Serbo Croatian</a> at 38%</li><li><a href='http://en.wikipedia.org/wiki/Aromanian_language' target='_blank'>Aromanian</a> at 38%</li><li><a href='http://en.wikipedia.org/wiki/Ladino_language' target='_blank'>Ladino</a> at 37%</li><li><a href='http://en.wikipedia.org/wiki/German_language' target='_blank'>German</a> at 37%</li><li><a href='http://en.wikipedia.org/wiki/JÃ¨rriais_language' target='_blank'>JÃ¨rriais</a> at 36%</li><li><a href='http://en.wikipedia.org/wiki/Tarantino_language' target='_blank'>Tarantino</a> at 36%</li><li><a href='http://en.wikipedia.org/wiki/Swedish_language' target='_blank'>Swedish</a> at 35%</li><li><a href='http://en.wikipedia.org/wiki/GuernÃ©siais_language' target='_blank'>GuernÃ©siais</a> at 35%</li><li><a href='http://en.wikipedia.org/wiki/Sicilian_language' target='_blank'>Sicilian</a> at 35%</li><li><a href='http://en.wikipedia.org/wiki/Dutch_language' target='_blank'>Dutch</a> at 35%</li><li><a href='http://en.wikipedia.org/wiki/Finnish_language' target='_blank'>Finnish</a> at 34%</li><li><a href='http://en.wikipedia.org/wiki/Russian_language' target='_blank'>Russian</a> at 33%</li><li><a href='http://en.wikipedia.org/wiki/Dalmatian_language' target='_blank'>Dalmatian</a> at 33%</li></ul>"},
                        {label: "Chamicuro", id: 11, color: "#85e982", textcolor: "#45a942", size: 5, desc: "Language: <a href='http://en.wikipedia.org/wiki/Chamicuro_language' target='_blank'>Chamicuro</a><br/><br/>No similar languages found."},
                        {label: "Classical Nahuatl", id: 12, color: "#bedeef", textcolor: "#7e9eaf", size: 5, desc: "Language: <a href='http://en.wikipedia.org/wiki/Classical_Nahuatl_language' target='_blank'>Classical Nahuatl</a><br/><br/>No similar languages found."},
                        {label: "Cornish", id: 13, color: "#e481ee", textcolor: "#a441ae", size: 5, desc: "Language: <a href='http://en.wikipedia.org/wiki/Cornish_language' target='_blank'>Cornish</a><br/><br/>Similar to:<ul><li><a href='http://en.wikipedia.org/wiki/Breton_language' target='_blank'>Breton</a> at 54%</li><li><a href='http://en.wikipedia.org/wiki/Welsh_language' target='_blank'>Welsh</a> at 48%</li></ul>"},
                        {label: "Crimean Tatar", id: 14, color: "#da98ac", textcolor: "#9a586c", size: 5, desc: "Language: <a href='http://en.wikipedia.org/wiki/Crimean_Tatar_language' target='_blank'>Crimean Tatar</a><br/><br/>Similar to:<ul><li><a href='http://en.wikipedia.org/wiki/Turkish_language' target='_blank'>Turkish</a> at 51%</li><li><a href='http://en.wikipedia.org/wiki/Azeri_language' target='_blank'>Azeri</a> at 50%</li><li><a href='http://en.wikipedia.org/wiki/Serbo_Croatian_language' target='_blank'>Serbo Croatian</a> at 35%</li><li><a href='http://en.wikipedia.org/wiki/Russian_language' target='_blank'>Russian</a> at 34%</li></ul>"},
                        {label: "Czech", id: 15, color: "#83a2c2", textcolor: "#436282", size: 8.2495, desc: "Language: <a href='http://en.wikipedia.org/wiki/Czech_language' target='_blank'>Czech</a><br/><br/>Similar to:<ul><li><a href='http://en.wikipedia.org/wiki/Slovak_language' target='_blank'>Slovak</a> at 57%</li><li><a href='http://en.wikipedia.org/wiki/Serbo_Croatian_language' target='_blank'>Serbo Croatian</a> at 50%</li><li><a href='http://en.wikipedia.org/wiki/Polish_language' target='_blank'>Polish</a> at 45%</li><li><a href='http://en.wikipedia.org/wiki/Russian_language' target='_blank'>Russian</a> at 44%</li><li><a href='http://en.wikipedia.org/wiki/Slovene_language' target='_blank'>Slovene</a> at 42%</li><li><a href='http://en.wikipedia.org/wiki/Bulgarian_language' target='_blank'>Bulgarian</a> at 41%</li><li><a href='http://en.wikipedia.org/wiki/Macedonian_language' target='_blank'>Macedonian</a> at 36%</li><li><a href='http://en.wikipedia.org/wiki/Italian_language' target='_blank'>Italian</a> at 36%</li><li><a href='http://en.wikipedia.org/wiki/Ukrainian_language' target='_blank'>Ukrainian</a> at 34%</li></ul>"},
                        {label: "Dalmatian", id: 16, color: "#d8b1ea", textcolor: "#9871aa", size: 5, desc: "Language: <a href='http://en.wikipedia.org/wiki/Dalmatian_language' target='_blank'>Dalmatian</a><br/><br/>Similar to:<ul><li><a href='http://en.wikipedia.org/wiki/Italian_language' target='_blank'>Italian</a> at 48%</li><li><a href='http://en.wikipedia.org/wiki/Spanish_language' target='_blank'>Spanish</a> at 44%</li><li><a href='http://en.wikipedia.org/wiki/Occitan_language' target='_blank'>Occitan</a> at 43%</li><li><a href='http://en.wikipedia.org/wiki/Romansch_language' target='_blank'>Romansch</a> at 43%</li><li><a href='http://en.wikipedia.org/wiki/French_language' target='_blank'>French</a> at 41%</li><li><a href='http://en.wikipedia.org/wiki/Romanian_language' target='_blank'>Romanian</a> at 40%</li><li><a href='http://en.wikipedia.org/wiki/Latin_language' target='_blank'>Latin</a> at 40%</li><li><a href='http://en.wikipedia.org/wiki/Venetian_language' target='_blank'>Venetian</a> at 39%</li><li><a href='http://en.wikipedia.org/wiki/Interlingua_language' target='_blank'>Interlingua</a> at 38%</li><li><a href='http://en.wikipedia.org/wiki/Portuguese_language' target='_blank'>Portuguese</a> at 38%</li><li><a href='http://en.wikipedia.org/wiki/Old_French_language' target='_blank'>Old French</a> at 37%</li><li><a href='http://en.wikipedia.org/wiki/Galician_language' target='_blank'>Galician</a> at 37%</li><li><a href='http://en.wikipedia.org/wiki/Aromanian_language' target='_blank'>Aromanian</a> at 36%</li><li><a href='http://en.wikipedia.org/wiki/Asturian_language' target='_blank'>Asturian</a> at 34%</li><li><a href='http://en.wikipedia.org/wiki/Middle_French_language' target='_blank'>Middle French</a> at 33%</li><li><a href='http://en.wikipedia.org/wiki/GuernÃ©siais_language' target='_blank'>GuernÃ©siais</a> at 33%</li><li><a href='http://en.wikipedia.org/wiki/Catalan_language' target='_blank'>Catalan</a> at 33%</li><li><a href='http://en.wikipedia.org/wiki/Sicilian_language' target='_blank'>Sicilian</a> at 33%</li></ul>"},
                        {label: "Danish", id: 17, color: "#f1b39f", textcolor: "#b1735f", size: 5, desc: "Language: <a href='http://en.wikipedia.org/wiki/Danish_language' target='_blank'>Danish</a><br/><br/>Similar to:<ul><li><a href='http://en.wikipedia.org/wiki/Swedish_language' target='_blank'>Swedish</a> at 61%</li><li><a href='http://en.wikipedia.org/wiki/Norwegian_language' target='_blank'>Norwegian</a> at 59%</li><li><a href='http://en.wikipedia.org/wiki/German_language' target='_blank'>German</a> at 48%</li><li><a href='http://en.wikipedia.org/wiki/Dutch_language' target='_blank'>Dutch</a> at 46%</li><li><a href='http://en.wikipedia.org/wiki/Norwegian_BokmÃ¥l_language' target='_blank'>Norwegian BokmÃ¥l</a> at 46%</li><li><a href='http://en.wikipedia.org/wiki/Norwegian_Nynorsk_language' target='_blank'>Norwegian Nynorsk</a> at 39%</li><li><a href='http://en.wikipedia.org/wiki/Afrikaans_language' target='_blank'>Afrikaans</a> at 38%</li><li><a href='http://en.wikipedia.org/wiki/English_language' target='_blank'>English</a> at 38%</li><li><a href='http://en.wikipedia.org/wiki/Icelandic_language' target='_blank'>Icelandic</a> at 38%</li><li><a href='http://en.wikipedia.org/wiki/Faroese_language' target='_blank'>Faroese</a> at 37%</li><li><a href='http://en.wikipedia.org/wiki/French_language' target='_blank'>French</a> at 36%</li><li><a href='http://en.wikipedia.org/wiki/Italian_language' target='_blank'>Italian</a> at 36%</li><li><a href='http://en.wikipedia.org/wiki/Spanish_language' target='_blank'>Spanish</a> at 33%</li><li><a href='http://en.wikipedia.org/wiki/Serbo_Croatian_language' target='_blank'>Serbo Croatian</a> at 33%</li></ul>"},
                        {label: "Dutch", id: 18, color: "#94deb2", textcolor: "#549e72", size: 9.13, desc: "Language: <a href='http://en.wikipedia.org/wiki/Dutch_language' target='_blank'>Dutch</a><br/><br/>Similar to:<ul><li><a href='http://en.wikipedia.org/wiki/Afrikaans_language' target='_blank'>Afrikaans</a> at 69%</li><li><a href='http://en.wikipedia.org/wiki/German_language' target='_blank'>German</a> at 48%</li><li><a href='http://en.wikipedia.org/wiki/Danish_language' target='_blank'>Danish</a> at 46%</li><li><a href='http://en.wikipedia.org/wiki/Swedish_language' target='_blank'>Swedish</a> at 37%</li><li><a href='http://en.wikipedia.org/wiki/Luxembourgish_language' target='_blank'>Luxembourgish</a> at 35%</li><li><a href='http://en.wikipedia.org/wiki/Catalan_language' target='_blank'>Catalan</a> at 35%</li><li><a href='http://en.wikipedia.org/wiki/French_language' target='_blank'>French</a> at 35%</li><li><a href='http://en.wikipedia.org/wiki/English_language' target='_blank'>English</a> at 34%</li><li><a href='http://en.wikipedia.org/wiki/Italian_language' target='_blank'>Italian</a> at 34%</li></ul>"},
                        {label: "English", id: 19, color: "#cfeb8a", textcolor: "#8fab4a", size: 32.7215, desc: "Language: <a href='http://en.wikipedia.org/wiki/English_language' target='_blank'>English</a><br/><br/>Similar to:<ul><li><a href='http://en.wikipedia.org/wiki/Catalan_language' target='_blank'>Catalan</a> at 50%</li><li><a href='http://en.wikipedia.org/wiki/Italian_language' target='_blank'>Italian</a> at 46%</li><li><a href='http://en.wikipedia.org/wiki/French_language' target='_blank'>French</a> at 45%</li><li><a href='http://en.wikipedia.org/wiki/Galician_language' target='_blank'>Galician</a> at 43%</li><li><a href='http://en.wikipedia.org/wiki/Spanish_language' target='_blank'>Spanish</a> at 43%</li><li><a href='http://en.wikipedia.org/wiki/Ido_language' target='_blank'>Ido</a> at 42%</li><li><a href='http://en.wikipedia.org/wiki/Asturian_language' target='_blank'>Asturian</a> at 41%</li><li><a href='http://en.wikipedia.org/wiki/Middle_English_language' target='_blank'>Middle English</a> at 41%</li><li><a href='http://en.wikipedia.org/wiki/Esperanto_language' target='_blank'>Esperanto</a> at 39%</li><li><a href='http://en.wikipedia.org/wiki/Danish_language' target='_blank'>Danish</a> at 38%</li><li><a href='http://en.wikipedia.org/wiki/German_language' target='_blank'>German</a> at 38%</li><li><a href='http://en.wikipedia.org/wiki/Portuguese_language' target='_blank'>Portuguese</a> at 38%</li><li><a href='http://en.wikipedia.org/wiki/Interlingua_language' target='_blank'>Interlingua</a> at 36%</li><li><a href='http://en.wikipedia.org/wiki/Anglo_Norman_language' target='_blank'>Anglo Norman</a> at 35%</li><li><a href='http://en.wikipedia.org/wiki/Dutch_language' target='_blank'>Dutch</a> at 34%</li><li><a href='http://en.wikipedia.org/wiki/Middle_French_language' target='_blank'>Middle French</a> at 34%</li><li><a href='http://en.wikipedia.org/wiki/Afrikaans_language' target='_blank'>Afrikaans</a> at 33%</li><li><a href='http://en.wikipedia.org/wiki/Norwegian_Nynorsk_language' target='_blank'>Norwegian Nynorsk</a> at 33%</li></ul>"},
                        {label: "Esperanto", id: 20, color: "#9da096", textcolor: "#5d6056", size: 5.3175, desc: "Language: <a href='http://en.wikipedia.org/wiki/Esperanto_language' target='_blank'>Esperanto</a><br/><br/>Similar to:<ul><li><a href='http://en.wikipedia.org/wiki/Ido_language' target='_blank'>Ido</a> at 66%</li><li><a href='http://en.wikipedia.org/wiki/Italian_language' target='_blank'>Italian</a> at 50%</li><li><a href='http://en.wikipedia.org/wiki/Spanish_language' target='_blank'>Spanish</a> at 48%</li><li><a href='http://en.wikipedia.org/wiki/French_language' target='_blank'>French</a> at 44%</li><li><a href='http://en.wikipedia.org/wiki/Catalan_language' target='_blank'>Catalan</a> at 44%</li><li><a href='http://en.wikipedia.org/wiki/Portuguese_language' target='_blank'>Portuguese</a> at 43%</li><li><a href='http://en.wikipedia.org/wiki/Galician_language' target='_blank'>Galician</a> at 42%</li><li><a href='http://en.wikipedia.org/wiki/Asturian_language' target='_blank'>Asturian</a> at 42%</li><li><a href='http://en.wikipedia.org/wiki/Latin_language' target='_blank'>Latin</a> at 41%</li><li><a href='http://en.wikipedia.org/wiki/Serbo_Croatian_language' target='_blank'>Serbo Croatian</a> at 40%</li><li><a href='http://en.wikipedia.org/wiki/English_language' target='_blank'>English</a> at 39%</li><li><a href='http://en.wikipedia.org/wiki/Interlingua_language' target='_blank'>Interlingua</a> at 38%</li><li><a href='http://en.wikipedia.org/wiki/German_language' target='_blank'>German</a> at 37%</li><li><a href='http://en.wikipedia.org/wiki/Romanian_language' target='_blank'>Romanian</a> at 36%</li><li><a href='http://en.wikipedia.org/wiki/Russian_language' target='_blank'>Russian</a> at 36%</li><li><a href='http://en.wikipedia.org/wiki/Swedish_language' target='_blank'>Swedish</a> at 35%</li><li><a href='http://en.wikipedia.org/wiki/Finnish_language' target='_blank'>Finnish</a> at 34%</li><li><a href='http://en.wikipedia.org/wiki/Anglo_Norman_language' target='_blank'>Anglo Norman</a> at 34%</li><li><a href='http://en.wikipedia.org/wiki/Venetian_language' target='_blank'>Venetian</a> at 33%</li></ul>"},
                        {label: "Estonian", id: 21, color: "#bcdeb4", textcolor: "#7c9e74", size: 5, desc: "Language: <a href='http://en.wikipedia.org/wiki/Estonian_language' target='_blank'>Estonian</a><br/><br/>Similar to:<ul><li><a href='http://en.wikipedia.org/wiki/Finnish_language' target='_blank'>Finnish</a> at 51%</li><li><a href='http://en.wikipedia.org/wiki/Serbo_Croatian_language' target='_blank'>Serbo Croatian</a> at 34%</li><li><a href='http://en.wikipedia.org/wiki/German_language' target='_blank'>German</a> at 33%</li><li><a href='http://en.wikipedia.org/wiki/Swedish_language' target='_blank'>Swedish</a> at 33%</li><li><a href='http://en.wikipedia.org/wiki/Russian_language' target='_blank'>Russian</a> at 33%</li></ul>"},
                        {label: "Faroese", id: 22, color: "#fadd9c", textcolor: "#ba9d5c", size: 5, desc: "Language: <a href='http://en.wikipedia.org/wiki/Faroese_language' target='_blank'>Faroese</a><br/><br/>Similar to:<ul><li><a href='http://en.wikipedia.org/wiki/Icelandic_language' target='_blank'>Icelandic</a> at 63%</li><li><a href='http://en.wikipedia.org/wiki/Old_Norse_language' target='_blank'>Old Norse</a> at 50%</li><li><a href='http://en.wikipedia.org/wiki/Swedish_language' target='_blank'>Swedish</a> at 49%</li><li><a href='http://en.wikipedia.org/wiki/Norwegian_language' target='_blank'>Norwegian</a> at 39%</li><li><a href='http://en.wikipedia.org/wiki/Norwegian_Nynorsk_language' target='_blank'>Norwegian Nynorsk</a> at 39%</li><li><a href='http://en.wikipedia.org/wiki/Danish_language' target='_blank'>Danish</a> at 37%</li><li><a href='http://en.wikipedia.org/wiki/Old_Saxon_language' target='_blank'>Old Saxon</a> at 33%</li></ul>"},
                        {label: "Finnish", id: 23, color: "#99cb8a", textcolor: "#598b4a", size: 23.1155, desc: "Language: <a href='http://en.wikipedia.org/wiki/Finnish_language' target='_blank'>Finnish</a><br/><br/>Similar to:<ul><li><a href='http://en.wikipedia.org/wiki/Estonian_language' target='_blank'>Estonian</a> at 51%</li><li><a href='http://en.wikipedia.org/wiki/Catalan_language' target='_blank'>Catalan</a> at 34%</li><li><a href='http://en.wikipedia.org/wiki/Esperanto_language' target='_blank'>Esperanto</a> at 34%</li><li><a href='http://en.wikipedia.org/wiki/Italian_language' target='_blank'>Italian</a> at 33%</li></ul>"},
                        {label: "French", id: 24, color: "#d1b897", textcolor: "#917857", size: 20.0855, desc: "Language: <a href='http://en.wikipedia.org/wiki/French_language' target='_blank'>French</a><br/><br/>Similar to:<ul><li><a href='http://en.wikipedia.org/wiki/Anglo_Norman_language' target='_blank'>Anglo Norman</a> at 66%</li><li><a href='http://en.wikipedia.org/wiki/Catalan_language' target='_blank'>Catalan</a> at 60%</li><li><a href='http://en.wikipedia.org/wiki/Italian_language' target='_blank'>Italian</a> at 55%</li><li><a href='http://en.wikipedia.org/wiki/Asturian_language' target='_blank'>Asturian</a> at 52%</li><li><a href='http://en.wikipedia.org/wiki/Spanish_language' target='_blank'>Spanish</a> at 49%</li><li><a href='http://en.wikipedia.org/wiki/Middle_French_language' target='_blank'>Middle French</a> at 46%</li><li><a href='http://en.wikipedia.org/wiki/English_language' target='_blank'>English</a> at 45%</li><li><a href='http://en.wikipedia.org/wiki/Esperanto_language' target='_blank'>Esperanto</a> at 44%</li><li><a href='http://en.wikipedia.org/wiki/Old_French_language' target='_blank'>Old French</a> at 42%</li><li><a href='http://en.wikipedia.org/wiki/Dalmatian_language' target='_blank'>Dalmatian</a> at 41%</li><li><a href='http://en.wikipedia.org/wiki/JÃ¨rriais_language' target='_blank'>JÃ¨rriais</a> at 40%</li><li><a href='http://en.wikipedia.org/wiki/Portuguese_language' target='_blank'>Portuguese</a> at 39%</li><li><a href='http://en.wikipedia.org/wiki/Latin_language' target='_blank'>Latin</a> at 38%</li><li><a href='http://en.wikipedia.org/wiki/Galician_language' target='_blank'>Galician</a> at 37%</li><li><a href='http://en.wikipedia.org/wiki/Aromanian_language' target='_blank'>Aromanian</a> at 37%</li><li><a href='http://en.wikipedia.org/wiki/GuernÃ©siais_language' target='_blank'>GuernÃ©siais</a> at 36%</li><li><a href='http://en.wikipedia.org/wiki/Danish_language' target='_blank'>Danish</a> at 36%</li><li><a href='http://en.wikipedia.org/wiki/German_language' target='_blank'>German</a> at 36%</li><li><a href='http://en.wikipedia.org/wiki/Interlingua_language' target='_blank'>Interlingua</a> at 35%</li><li><a href='http://en.wikipedia.org/wiki/Dutch_language' target='_blank'>Dutch</a> at 35%</li><li><a href='http://en.wikipedia.org/wiki/Occitan_language' target='_blank'>Occitan</a> at 34%</li><li><a href='http://en.wikipedia.org/wiki/Ido_language' target='_blank'>Ido</a> at 33%</li><li><a href='http://en.wikipedia.org/wiki/Albanian_language' target='_blank'>Albanian</a> at 33%</li></ul>"},
                        {label: "Galician", id: 25, color: "#8fef82", textcolor: "#4faf42", size: 5, desc: "Language: <a href='http://en.wikipedia.org/wiki/Galician_language' target='_blank'>Galician</a><br/><br/>Similar to:<ul><li><a href='http://en.wikipedia.org/wiki/Spanish_language' target='_blank'>Spanish</a> at 84%</li><li><a href='http://en.wikipedia.org/wiki/Portuguese_language' target='_blank'>Portuguese</a> at 77%</li><li><a href='http://en.wikipedia.org/wiki/Asturian_language' target='_blank'>Asturian</a> at 71%</li><li><a href='http://en.wikipedia.org/wiki/Italian_language' target='_blank'>Italian</a> at 70%</li><li><a href='http://en.wikipedia.org/wiki/Catalan_language' target='_blank'>Catalan</a> at 60%</li><li><a href='http://en.wikipedia.org/wiki/Interlingua_language' target='_blank'>Interlingua</a> at 57%</li><li><a href='http://en.wikipedia.org/wiki/Occitan_language' target='_blank'>Occitan</a> at 52%</li><li><a href='http://en.wikipedia.org/wiki/Latin_language' target='_blank'>Latin</a> at 49%</li><li><a href='http://en.wikipedia.org/wiki/Venetian_language' target='_blank'>Venetian</a> at 49%</li><li><a href='http://en.wikipedia.org/wiki/Ido_language' target='_blank'>Ido</a> at 48%</li><li><a href='http://en.wikipedia.org/wiki/Ladino_language' target='_blank'>Ladino</a> at 47%</li><li><a href='http://en.wikipedia.org/wiki/Romanian_language' target='_blank'>Romanian</a> at 47%</li><li><a href='http://en.wikipedia.org/wiki/Romansch_language' target='_blank'>Romansch</a> at 44%</li><li><a href='http://en.wikipedia.org/wiki/English_language' target='_blank'>English</a> at 43%</li><li><a href='http://en.wikipedia.org/wiki/Esperanto_language' target='_blank'>Esperanto</a> at 42%</li><li><a href='http://en.wikipedia.org/wiki/Old_French_language' target='_blank'>Old French</a> at 40%</li><li><a href='http://en.wikipedia.org/wiki/Tarantino_language' target='_blank'>Tarantino</a> at 39%</li><li><a href='http://en.wikipedia.org/wiki/Anglo_Norman_language' target='_blank'>Anglo Norman</a> at 39%</li><li><a href='http://en.wikipedia.org/wiki/Serbo_Croatian_language' target='_blank'>Serbo Croatian</a> at 38%</li><li><a href='http://en.wikipedia.org/wiki/Sicilian_language' target='_blank'>Sicilian</a> at 38%</li><li><a href='http://en.wikipedia.org/wiki/Dalmatian_language' target='_blank'>Dalmatian</a> at 37%</li><li><a href='http://en.wikipedia.org/wiki/French_language' target='_blank'>French</a> at 37%</li><li><a href='http://en.wikipedia.org/wiki/Middle_French_language' target='_blank'>Middle French</a> at 37%</li><li><a href='http://en.wikipedia.org/wiki/German_language' target='_blank'>German</a> at 37%</li><li><a href='http://en.wikipedia.org/wiki/Neapolitan_language' target='_blank'>Neapolitan</a> at 36%</li><li><a href='http://en.wikipedia.org/wiki/JÃ¨rriais_language' target='_blank'>JÃ¨rriais</a> at 35%</li><li><a href='http://en.wikipedia.org/wiki/Aromanian_language' target='_blank'>Aromanian</a> at 35%</li><li><a href='http://en.wikipedia.org/wiki/Russian_language' target='_blank'>Russian</a> at 34%</li><li><a href='http://en.wikipedia.org/wiki/Swedish_language' target='_blank'>Swedish</a> at 34%</li><li><a href='http://en.wikipedia.org/wiki/GuernÃ©siais_language' target='_blank'>GuernÃ©siais</a> at 33%</li></ul>"},
                        {label: "German", id: 26, color: "#cdd8a7", textcolor: "#8d9867", size: 11.6355, desc: "Language: <a href='http://en.wikipedia.org/wiki/German_language' target='_blank'>German</a><br/><br/>Similar to:<ul><li><a href='http://en.wikipedia.org/wiki/Dutch_language' target='_blank'>Dutch</a> at 48%</li><li><a href='http://en.wikipedia.org/wiki/Danish_language' target='_blank'>Danish</a> at 48%</li><li><a href='http://en.wikipedia.org/wiki/Luxembourgish_language' target='_blank'>Luxembourgish</a> at 46%</li><li><a href='http://en.wikipedia.org/wiki/Afrikaans_language' target='_blank'>Afrikaans</a> at 45%</li><li><a href='http://en.wikipedia.org/wiki/Swedish_language' target='_blank'>Swedish</a> at 41%</li><li><a href='http://en.wikipedia.org/wiki/English_language' target='_blank'>English</a> at 38%</li><li><a href='http://en.wikipedia.org/wiki/Esperanto_language' target='_blank'>Esperanto</a> at 37%</li><li><a href='http://en.wikipedia.org/wiki/Catalan_language' target='_blank'>Catalan</a> at 37%</li><li><a href='http://en.wikipedia.org/wiki/Italian_language' target='_blank'>Italian</a> at 37%</li><li><a href='http://en.wikipedia.org/wiki/Galician_language' target='_blank'>Galician</a> at 37%</li><li><a href='http://en.wikipedia.org/wiki/French_language' target='_blank'>French</a> at 36%</li><li><a href='http://en.wikipedia.org/wiki/Asturian_language' target='_blank'>Asturian</a> at 36%</li><li><a href='http://en.wikipedia.org/wiki/Old_High_German_language' target='_blank'>Old High German</a> at 36%</li><li><a href='http://en.wikipedia.org/wiki/Spanish_language' target='_blank'>Spanish</a> at 34%</li><li><a href='http://en.wikipedia.org/wiki/Norwegian_language' target='_blank'>Norwegian</a> at 34%</li><li><a href='http://en.wikipedia.org/wiki/Estonian_language' target='_blank'>Estonian</a> at 33%</li></ul>"},
                        {label: "Greek", id: 27, color: "#b7aa80", textcolor: "#776a40", size: 6.7925, desc: "Language: <a href='http://en.wikipedia.org/wiki/Greek_language' target='_blank'>Greek</a><br/><br/>No similar languages found."},
                        {label: "Greenlandic", id: 28, color: "#efdeeb", textcolor: "#af9eab", size: 5, desc: "Language: <a href='http://en.wikipedia.org/wiki/Greenlandic_language' target='_blank'>Greenlandic</a><br/><br/>No similar languages found."},
                        {label: "GuernÃ©siais", id: 29, color: "#addee1", textcolor: "#6d9ea1", size: 5, desc: "Language: <a href='http://en.wikipedia.org/wiki/GuernÃ©siais_language' target='_blank'>GuernÃ©siais</a><br/><br/>Similar to:<ul><li><a href='http://en.wikipedia.org/wiki/JÃ¨rriais_language' target='_blank'>JÃ¨rriais</a> at 64%</li><li><a href='http://en.wikipedia.org/wiki/Old_French_language' target='_blank'>Old French</a> at 56%</li><li><a href='http://en.wikipedia.org/wiki/Middle_French_language' target='_blank'>Middle French</a> at 54%</li><li><a href='http://en.wikipedia.org/wiki/Occitan_language' target='_blank'>Occitan</a> at 48%</li><li><a href='http://en.wikipedia.org/wiki/Anglo_Norman_language' target='_blank'>Anglo Norman</a> at 47%</li><li><a href='http://en.wikipedia.org/wiki/Interlingua_language' target='_blank'>Interlingua</a> at 45%</li><li><a href='http://en.wikipedia.org/wiki/Italian_language' target='_blank'>Italian</a> at 43%</li><li><a href='http://en.wikipedia.org/wiki/Spanish_language' target='_blank'>Spanish</a> at 40%</li><li><a href='http://en.wikipedia.org/wiki/Romansch_language' target='_blank'>Romansch</a> at 39%</li><li><a href='http://en.wikipedia.org/wiki/Haitian_Creole_language' target='_blank'>Haitian Creole</a> at 39%</li><li><a href='http://en.wikipedia.org/wiki/Portuguese_language' target='_blank'>Portuguese</a> at 37%</li><li><a href='http://en.wikipedia.org/wiki/French_language' target='_blank'>French</a> at 36%</li><li><a href='http://en.wikipedia.org/wiki/Tarantino_language' target='_blank'>Tarantino</a> at 36%</li><li><a href='http://en.wikipedia.org/wiki/Venetian_language' target='_blank'>Venetian</a> at 36%</li><li><a href='http://en.wikipedia.org/wiki/Catalan_language' target='_blank'>Catalan</a> at 35%</li><li><a href='http://en.wikipedia.org/wiki/Latin_language' target='_blank'>Latin</a> at 34%</li><li><a href='http://en.wikipedia.org/wiki/Asturian_language' target='_blank'>Asturian</a> at 33%</li><li><a href='http://en.wikipedia.org/wiki/Dalmatian_language' target='_blank'>Dalmatian</a> at 33%</li><li><a href='http://en.wikipedia.org/wiki/Ido_language' target='_blank'>Ido</a> at 33%</li><li><a href='http://en.wikipedia.org/wiki/Galician_language' target='_blank'>Galician</a> at 33%</li></ul>"},
                        {label: "Haitian Creole", id: 30, color: "#a6cd8a", textcolor: "#668d4a", size: 5, desc: "Language: <a href='http://en.wikipedia.org/wiki/Haitian_Creole_language' target='_blank'>Haitian Creole</a><br/><br/>Similar to:<ul><li><a href='http://en.wikipedia.org/wiki/Italian_language' target='_blank'>Italian</a> at 41%</li><li><a href='http://en.wikipedia.org/wiki/JÃ¨rriais_language' target='_blank'>JÃ¨rriais</a> at 40%</li><li><a href='http://en.wikipedia.org/wiki/GuernÃ©siais_language' target='_blank'>GuernÃ©siais</a> at 39%</li><li><a href='http://en.wikipedia.org/wiki/Spanish_language' target='_blank'>Spanish</a> at 38%</li><li><a href='http://en.wikipedia.org/wiki/Old_French_language' target='_blank'>Old French</a> at 36%</li><li><a href='http://en.wikipedia.org/wiki/Middle_French_language' target='_blank'>Middle French</a> at 35%</li><li><a href='http://en.wikipedia.org/wiki/Occitan_language' target='_blank'>Occitan</a> at 34%</li><li><a href='http://en.wikipedia.org/wiki/Portuguese_language' target='_blank'>Portuguese</a> at 33%</li></ul>"},
                        {label: "Hawaiian", id: 31, color: "#94f9b4", textcolor: "#54b974", size: 5, desc: "Language: <a href='http://en.wikipedia.org/wiki/Hawaiian_language' target='_blank'>Hawaiian</a><br/><br/>No similar languages found."},
                        {label: "Hiligaynon", id: 32, color: "#caa691", textcolor: "#8a6651", size: 5, desc: "Language: <a href='http://en.wikipedia.org/wiki/Hiligaynon_language' target='_blank'>Hiligaynon</a><br/><br/>Similar to:<ul><li><a href='http://en.wikipedia.org/wiki/Tagalog_language' target='_blank'>Tagalog</a> at 46%</li><li><a href='http://en.wikipedia.org/wiki/Spanish_language' target='_blank'>Spanish</a> at 35%</li></ul>"},
                        {label: "Hungarian", id: 33, color: "#9c89c3", textcolor: "#5c4983", size: 8.8885, desc: "Language: <a href='http://en.wikipedia.org/wiki/Hungarian_language' target='_blank'>Hungarian</a><br/><br/>Similar to:<ul><li><a href='http://en.wikipedia.org/wiki/Serbo_Croatian_language' target='_blank'>Serbo Croatian</a> at 33%</li></ul>"},
                        {label: "Icelandic", id: 34, color: "#c0eefa", textcolor: "#80aeba", size: 6.546, desc: "Language: <a href='http://en.wikipedia.org/wiki/Icelandic_language' target='_blank'>Icelandic</a><br/><br/>Similar to:<ul><li><a href='http://en.wikipedia.org/wiki/Faroese_language' target='_blank'>Faroese</a> at 63%</li><li><a href='http://en.wikipedia.org/wiki/Old_Norse_language' target='_blank'>Old Norse</a> at 49%</li><li><a href='http://en.wikipedia.org/wiki/Swedish_language' target='_blank'>Swedish</a> at 42%</li><li><a href='http://en.wikipedia.org/wiki/Danish_language' target='_blank'>Danish</a> at 38%</li><li><a href='http://en.wikipedia.org/wiki/Norwegian_language' target='_blank'>Norwegian</a> at 33%</li></ul>"},
                        {label: "Ido", id: 35, color: "#e4b8d3", textcolor: "#a47893", size: 5, desc: "Language: <a href='http://en.wikipedia.org/wiki/Ido_language' target='_blank'>Ido</a><br/><br/>Similar to:<ul><li><a href='http://en.wikipedia.org/wiki/Esperanto_language' target='_blank'>Esperanto</a> at 66%</li><li><a href='http://en.wikipedia.org/wiki/Italian_language' target='_blank'>Italian</a> at 58%</li><li><a href='http://en.wikipedia.org/wiki/Spanish_language' target='_blank'>Spanish</a> at 57%</li><li><a href='http://en.wikipedia.org/wiki/Interlingua_language' target='_blank'>Interlingua</a> at 50%</li><li><a href='http://en.wikipedia.org/wiki/Portuguese_language' target='_blank'>Portuguese</a> at 50%</li><li><a href='http://en.wikipedia.org/wiki/Galician_language' target='_blank'>Galician</a> at 48%</li><li><a href='http://en.wikipedia.org/wiki/Latin_language' target='_blank'>Latin</a> at 44%</li><li><a href='http://en.wikipedia.org/wiki/Catalan_language' target='_blank'>Catalan</a> at 44%</li><li><a href='http://en.wikipedia.org/wiki/Occitan_language' target='_blank'>Occitan</a> at 43%</li><li><a href='http://en.wikipedia.org/wiki/Asturian_language' target='_blank'>Asturian</a> at 42%</li><li><a href='http://en.wikipedia.org/wiki/English_language' target='_blank'>English</a> at 42%</li><li><a href='http://en.wikipedia.org/wiki/Venetian_language' target='_blank'>Venetian</a> at 41%</li><li><a href='http://en.wikipedia.org/wiki/Old_French_language' target='_blank'>Old French</a> at 38%</li><li><a href='http://en.wikipedia.org/wiki/Romanian_language' target='_blank'>Romanian</a> at 38%</li><li><a href='http://en.wikipedia.org/wiki/Middle_French_language' target='_blank'>Middle French</a> at 38%</li><li><a href='http://en.wikipedia.org/wiki/Serbo_Croatian_language' target='_blank'>Serbo Croatian</a> at 38%</li><li><a href='http://en.wikipedia.org/wiki/Romansch_language' target='_blank'>Romansch</a> at 36%</li><li><a href='http://en.wikipedia.org/wiki/Ladino_language' target='_blank'>Ladino</a> at 36%</li><li><a href='http://en.wikipedia.org/wiki/Swedish_language' target='_blank'>Swedish</a> at 35%</li><li><a href='http://en.wikipedia.org/wiki/Anglo_Norman_language' target='_blank'>Anglo Norman</a> at 35%</li><li><a href='http://en.wikipedia.org/wiki/JÃ¨rriais_language' target='_blank'>JÃ¨rriais</a> at 34%</li><li><a href='http://en.wikipedia.org/wiki/Russian_language' target='_blank'>Russian</a> at 33%</li><li><a href='http://en.wikipedia.org/wiki/GuernÃ©siais_language' target='_blank'>GuernÃ©siais</a> at 33%</li><li><a href='http://en.wikipedia.org/wiki/French_language' target='_blank'>French</a> at 33%</li></ul>"},
                        {label: "Indonesian", id: 36, color: "#f7b198", textcolor: "#b77158", size: 5, desc: "Language: <a href='http://en.wikipedia.org/wiki/Indonesian_language' target='_blank'>Indonesian</a><br/><br/>Similar to:<ul><li><a href='http://en.wikipedia.org/wiki/Malay_language' target='_blank'>Malay</a> at 79%</li></ul>"},
                        {label: "Interlingua", id: 37, color: "#a3eef9", textcolor: "#63aeb9", size: 5, desc: "Language: <a href='http://en.wikipedia.org/wiki/Interlingua_language' target='_blank'>Interlingua</a><br/><br/>Similar to:<ul><li><a href='http://en.wikipedia.org/wiki/Italian_language' target='_blank'>Italian</a> at 72%</li><li><a href='http://en.wikipedia.org/wiki/Spanish_language' target='_blank'>Spanish</a> at 69%</li><li><a href='http://en.wikipedia.org/wiki/Portuguese_language' target='_blank'>Portuguese</a> at 62%</li><li><a href='http://en.wikipedia.org/wiki/Latin_language' target='_blank'>Latin</a> at 58%</li><li><a href='http://en.wikipedia.org/wiki/Galician_language' target='_blank'>Galician</a> at 57%</li><li><a href='http://en.wikipedia.org/wiki/Occitan_language' target='_blank'>Occitan</a> at 56%</li><li><a href='http://en.wikipedia.org/wiki/Venetian_language' target='_blank'>Venetian</a> at 53%</li><li><a href='http://en.wikipedia.org/wiki/Catalan_language' target='_blank'>Catalan</a> at 50%</li><li><a href='http://en.wikipedia.org/wiki/Ido_language' target='_blank'>Ido</a> at 50%</li><li><a href='http://en.wikipedia.org/wiki/Asturian_language' target='_blank'>Asturian</a> at 49%</li><li><a href='http://en.wikipedia.org/wiki/Romanian_language' target='_blank'>Romanian</a> at 49%</li><li><a href='http://en.wikipedia.org/wiki/Old_French_language' target='_blank'>Old French</a> at 49%</li><li><a href='http://en.wikipedia.org/wiki/Tarantino_language' target='_blank'>Tarantino</a> at 49%</li><li><a href='http://en.wikipedia.org/wiki/Romansch_language' target='_blank'>Romansch</a> at 48%</li><li><a href='http://en.wikipedia.org/wiki/Middle_French_language' target='_blank'>Middle French</a> at 48%</li><li><a href='http://en.wikipedia.org/wiki/Sicilian_language' target='_blank'>Sicilian</a> at 47%</li><li><a href='http://en.wikipedia.org/wiki/GuernÃ©siais_language' target='_blank'>GuernÃ©siais</a> at 45%</li><li><a href='http://en.wikipedia.org/wiki/Anglo_Norman_language' target='_blank'>Anglo Norman</a> at 45%</li><li><a href='http://en.wikipedia.org/wiki/Neapolitan_language' target='_blank'>Neapolitan</a> at 44%</li><li><a href='http://en.wikipedia.org/wiki/Ladino_language' target='_blank'>Ladino</a> at 43%</li><li><a href='http://en.wikipedia.org/wiki/JÃ¨rriais_language' target='_blank'>JÃ¨rriais</a> at 42%</li><li><a href='http://en.wikipedia.org/wiki/Dalmatian_language' target='_blank'>Dalmatian</a> at 38%</li><li><a href='http://en.wikipedia.org/wiki/Esperanto_language' target='_blank'>Esperanto</a> at 38%</li><li><a href='http://en.wikipedia.org/wiki/English_language' target='_blank'>English</a> at 36%</li><li><a href='http://en.wikipedia.org/wiki/French_language' target='_blank'>French</a> at 35%</li><li><a href='http://en.wikipedia.org/wiki/Swedish_language' target='_blank'>Swedish</a> at 34%</li><li><a href='http://en.wikipedia.org/wiki/Aromanian_language' target='_blank'>Aromanian</a> at 34%</li><li><a href='http://en.wikipedia.org/wiki/Serbo_Croatian_language' target='_blank'>Serbo Croatian</a> at 33%</li></ul>"},
                        {label: "Irish", id: 38, color: "#a7caf7", textcolor: "#678ab7", size: 6.1205, desc: "Language: <a href='http://en.wikipedia.org/wiki/Irish_language' target='_blank'>Irish</a><br/><br/>Similar to:<ul><li><a href='http://en.wikipedia.org/wiki/Scottish_Gaelic_language' target='_blank'>Scottish Gaelic</a> at 53%</li><li><a href='http://en.wikipedia.org/wiki/Old_Irish_language' target='_blank'>Old Irish</a> at 43%</li><li><a href='http://en.wikipedia.org/wiki/Italian_language' target='_blank'>Italian</a> at 33%</li></ul>"},
                        {label: "Italian", id: 39, color: "#a28eee", textcolor: "#624eae", size: 32.7215, desc: "Language: <a href='http://en.wikipedia.org/wiki/Italian_language' target='_blank'>Italian</a><br/><br/>Similar to:<ul><li><a href='http://en.wikipedia.org/wiki/Interlingua_language' target='_blank'>Interlingua</a> at 72%</li><li><a href='http://en.wikipedia.org/wiki/Galician_language' target='_blank'>Galician</a> at 70%</li><li><a href='http://en.wikipedia.org/wiki/Catalan_language' target='_blank'>Catalan</a> at 67%</li><li><a href='http://en.wikipedia.org/wiki/Asturian_language' target='_blank'>Asturian</a> at 61%</li><li><a href='http://en.wikipedia.org/wiki/Ido_language' target='_blank'>Ido</a> at 58%</li><li><a href='http://en.wikipedia.org/wiki/French_language' target='_blank'>French</a> at 55%</li><li><a href='http://en.wikipedia.org/wiki/Spanish_language' target='_blank'>Spanish</a> at 54%</li><li><a href='http://en.wikipedia.org/wiki/Esperanto_language' target='_blank'>Esperanto</a> at 50%</li><li><a href='http://en.wikipedia.org/wiki/Anglo_Norman_language' target='_blank'>Anglo Norman</a> at 50%</li><li><a href='http://en.wikipedia.org/wiki/Dalmatian_language' target='_blank'>Dalmatian</a> at 48%</li><li><a href='http://en.wikipedia.org/wiki/Aromanian_language' target='_blank'>Aromanian</a> at 46%</li><li><a href='http://en.wikipedia.org/wiki/English_language' target='_blank'>English</a> at 46%</li><li><a href='http://en.wikipedia.org/wiki/GuernÃ©siais_language' target='_blank'>GuernÃ©siais</a> at 43%</li><li><a href='http://en.wikipedia.org/wiki/Portuguese_language' target='_blank'>Portuguese</a> at 41%</li><li><a href='http://en.wikipedia.org/wiki/Latin_language' target='_blank'>Latin</a> at 41%</li><li><a href='http://en.wikipedia.org/wiki/Haitian_Creole_language' target='_blank'>Haitian Creole</a> at 41%</li><li><a href='http://en.wikipedia.org/wiki/German_language' target='_blank'>German</a> at 37%</li><li><a href='http://en.wikipedia.org/wiki/Danish_language' target='_blank'>Danish</a> at 36%</li><li><a href='http://en.wikipedia.org/wiki/Czech_language' target='_blank'>Czech</a> at 36%</li><li><a href='http://en.wikipedia.org/wiki/Venetian_language' target='_blank'>Venetian</a> at 35%</li><li><a href='http://en.wikipedia.org/wiki/Bulgarian_language' target='_blank'>Bulgarian</a> at 35%</li><li><a href='http://en.wikipedia.org/wiki/Albanian_language' target='_blank'>Albanian</a> at 34%</li><li><a href='http://en.wikipedia.org/wiki/Dutch_language' target='_blank'>Dutch</a> at 34%</li><li><a href='http://en.wikipedia.org/wiki/Basque_language' target='_blank'>Basque</a> at 34%</li><li><a href='http://en.wikipedia.org/wiki/Irish_language' target='_blank'>Irish</a> at 33%</li><li><a href='http://en.wikipedia.org/wiki/Romanian_language' target='_blank'>Romanian</a> at 33%</li><li><a href='http://en.wikipedia.org/wiki/Finnish_language' target='_blank'>Finnish</a> at 33%</li></ul>"},
                        {label: "JÃ¨rriais", id: 40, color: "#e98389", textcolor: "#a94349", size: 5, desc: "Language: <a href='http://en.wikipedia.org/wiki/JÃ¨rriais_language' target='_blank'>JÃ¨rriais</a><br/><br/>Similar to:<ul><li><a href='http://en.wikipedia.org/wiki/GuernÃ©siais_language' target='_blank'>GuernÃ©siais</a> at 64%</li><li><a href='http://en.wikipedia.org/wiki/Middle_French_language' target='_blank'>Middle French</a> at 55%</li><li><a href='http://en.wikipedia.org/wiki/Old_French_language' target='_blank'>Old French</a> at 51%</li><li><a href='http://en.wikipedia.org/wiki/Anglo_Norman_language' target='_blank'>Anglo Norman</a> at 47%</li><li><a href='http://en.wikipedia.org/wiki/Interlingua_language' target='_blank'>Interlingua</a> at 42%</li><li><a href='http://en.wikipedia.org/wiki/Occitan_language' target='_blank'>Occitan</a> at 41%</li><li><a href='http://en.wikipedia.org/wiki/French_language' target='_blank'>French</a> at 40%</li><li><a href='http://en.wikipedia.org/wiki/Haitian_Creole_language' target='_blank'>Haitian Creole</a> at 40%</li><li><a href='http://en.wikipedia.org/wiki/Romansch_language' target='_blank'>Romansch</a> at 37%</li><li><a href='http://en.wikipedia.org/wiki/Spanish_language' target='_blank'>Spanish</a> at 37%</li><li><a href='http://en.wikipedia.org/wiki/Tarantino_language' target='_blank'>Tarantino</a> at 37%</li><li><a href='http://en.wikipedia.org/wiki/Catalan_language' target='_blank'>Catalan</a> at 36%</li><li><a href='http://en.wikipedia.org/wiki/Galician_language' target='_blank'>Galician</a> at 35%</li><li><a href='http://en.wikipedia.org/wiki/Portuguese_language' target='_blank'>Portuguese</a> at 35%</li><li><a href='http://en.wikipedia.org/wiki/Ido_language' target='_blank'>Ido</a> at 34%</li><li><a href='http://en.wikipedia.org/wiki/Latin_language' target='_blank'>Latin</a> at 33%</li><li><a href='http://en.wikipedia.org/wiki/Asturian_language' target='_blank'>Asturian</a> at 33%</li></ul>"},
                        {label: "Kashubian", id: 41, color: "#a6da8b", textcolor: "#669a4b", size: 5, desc: "Language: <a href='http://en.wikipedia.org/wiki/Kashubian_language' target='_blank'>Kashubian</a><br/><br/>Similar to:<ul><li><a href='http://en.wikipedia.org/wiki/Polish_language' target='_blank'>Polish</a> at 55%</li><li><a href='http://en.wikipedia.org/wiki/Slovak_language' target='_blank'>Slovak</a> at 49%</li><li><a href='http://en.wikipedia.org/wiki/Serbo_Croatian_language' target='_blank'>Serbo Croatian</a> at 46%</li><li><a href='http://en.wikipedia.org/wiki/Ukrainian_language' target='_blank'>Ukrainian</a> at 44%</li><li><a href='http://en.wikipedia.org/wiki/Macedonian_language' target='_blank'>Macedonian</a> at 44%</li><li><a href='http://en.wikipedia.org/wiki/Slovene_language' target='_blank'>Slovene</a> at 42%</li><li><a href='http://en.wikipedia.org/wiki/Russian_language' target='_blank'>Russian</a> at 41%</li></ul>"},
                        {label: "Kurdish", id: 42, color: "#dcd0d4", textcolor: "#9c9094", size: 5, desc: "Language: <a href='http://en.wikipedia.org/wiki/Kurdish_language' target='_blank'>Kurdish</a><br/><br/>No similar languages found."},
                        {label: "Ladino", id: 43, color: "#aea3b2", textcolor: "#6e6372", size: 5, desc: "Language: <a href='http://en.wikipedia.org/wiki/Ladino_language' target='_blank'>Ladino</a><br/><br/>Similar to:<ul><li><a href='http://en.wikipedia.org/wiki/Spanish_language' target='_blank'>Spanish</a> at 57%</li><li><a href='http://en.wikipedia.org/wiki/Asturian_language' target='_blank'>Asturian</a> at 47%</li><li><a href='http://en.wikipedia.org/wiki/Galician_language' target='_blank'>Galician</a> at 47%</li><li><a href='http://en.wikipedia.org/wiki/Portuguese_language' target='_blank'>Portuguese</a> at 46%</li><li><a href='http://en.wikipedia.org/wiki/Interlingua_language' target='_blank'>Interlingua</a> at 43%</li><li><a href='http://en.wikipedia.org/wiki/Venetian_language' target='_blank'>Venetian</a> at 39%</li><li><a href='http://en.wikipedia.org/wiki/Occitan_language' target='_blank'>Occitan</a> at 38%</li><li><a href='http://en.wikipedia.org/wiki/Catalan_language' target='_blank'>Catalan</a> at 37%</li><li><a href='http://en.wikipedia.org/wiki/Ido_language' target='_blank'>Ido</a> at 36%</li><li><a href='http://en.wikipedia.org/wiki/Latin_language' target='_blank'>Latin</a> at 33%</li></ul>"},
                        {label: "Latin", id: 44, color: "#ca8dad", textcolor: "#8a4d6d", size: 14.574, desc: "Language: <a href='http://en.wikipedia.org/wiki/Latin_language' target='_blank'>Latin</a><br/><br/>Similar to:<ul><li><a href='http://en.wikipedia.org/wiki/Interlingua_language' target='_blank'>Interlingua</a> at 58%</li><li><a href='http://en.wikipedia.org/wiki/Catalan_language' target='_blank'>Catalan</a> at 49%</li><li><a href='http://en.wikipedia.org/wiki/Galician_language' target='_blank'>Galician</a> at 49%</li><li><a href='http://en.wikipedia.org/wiki/Asturian_language' target='_blank'>Asturian</a> at 49%</li><li><a href='http://en.wikipedia.org/wiki/Aromanian_language' target='_blank'>Aromanian</a> at 45%</li><li><a href='http://en.wikipedia.org/wiki/Ido_language' target='_blank'>Ido</a> at 44%</li><li><a href='http://en.wikipedia.org/wiki/Spanish_language' target='_blank'>Spanish</a> at 42%</li><li><a href='http://en.wikipedia.org/wiki/Esperanto_language' target='_blank'>Esperanto</a> at 41%</li><li><a href='http://en.wikipedia.org/wiki/Italian_language' target='_blank'>Italian</a> at 41%</li><li><a href='http://en.wikipedia.org/wiki/Anglo_Norman_language' target='_blank'>Anglo Norman</a> at 40%</li><li><a href='http://en.wikipedia.org/wiki/Dalmatian_language' target='_blank'>Dalmatian</a> at 40%</li><li><a href='http://en.wikipedia.org/wiki/French_language' target='_blank'>French</a> at 38%</li><li><a href='http://en.wikipedia.org/wiki/GuernÃ©siais_language' target='_blank'>GuernÃ©siais</a> at 34%</li><li><a href='http://en.wikipedia.org/wiki/JÃ¨rriais_language' target='_blank'>JÃ¨rriais</a> at 33%</li><li><a href='http://en.wikipedia.org/wiki/Portuguese_language' target='_blank'>Portuguese</a> at 33%</li><li><a href='http://en.wikipedia.org/wiki/Ladino_language' target='_blank'>Ladino</a> at 33%</li></ul>"},
                        {label: "Latvian", id: 45, color: "#98fc93", textcolor: "#58bc53", size: 5, desc: "Language: <a href='http://en.wikipedia.org/wiki/Latvian_language' target='_blank'>Latvian</a><br/><br/>Similar to:<ul><li><a href='http://en.wikipedia.org/wiki/Lithuanian_language' target='_blank'>Lithuanian</a> at 46%</li><li><a href='http://en.wikipedia.org/wiki/Serbo_Croatian_language' target='_blank'>Serbo Croatian</a> at 38%</li><li><a href='http://en.wikipedia.org/wiki/Russian_language' target='_blank'>Russian</a> at 36%</li></ul>"},
                        {label: "Limburgish", id: 46, color: "#c9a390", textcolor: "#896350", size: 5, desc: "Language: <a href='http://en.wikipedia.org/wiki/Limburgish_language' target='_blank'>Limburgish</a><br/><br/>Similar to:<ul><li><a href='http://en.wikipedia.org/wiki/West_Frisian_language' target='_blank'>West Frisian</a> at 47%</li><li><a href='http://en.wikipedia.org/wiki/Norwegian_language' target='_blank'>Norwegian</a> at 45%</li><li><a href='http://en.wikipedia.org/wiki/Swedish_language' target='_blank'>Swedish</a> at 45%</li><li><a href='http://en.wikipedia.org/wiki/Luxembourgish_language' target='_blank'>Luxembourgish</a> at 41%</li><li><a href='http://en.wikipedia.org/wiki/Afrikaans_language' target='_blank'>Afrikaans</a> at 39%</li><li><a href='http://en.wikipedia.org/wiki/Middle_English_language' target='_blank'>Middle English</a> at 34%</li></ul>"},
                        {label: "Lithuanian", id: 47, color: "#8ea1c3", textcolor: "#4e6183", size: 5, desc: "Language: <a href='http://en.wikipedia.org/wiki/Lithuanian_language' target='_blank'>Lithuanian</a><br/><br/>Similar to:<ul><li><a href='http://en.wikipedia.org/wiki/Latvian_language' target='_blank'>Latvian</a> at 46%</li><li><a href='http://en.wikipedia.org/wiki/Serbo_Croatian_language' target='_blank'>Serbo Croatian</a> at 43%</li><li><a href='http://en.wikipedia.org/wiki/Russian_language' target='_blank'>Russian</a> at 39%</li><li><a href='http://en.wikipedia.org/wiki/Slovene_language' target='_blank'>Slovene</a> at 36%</li></ul>"},
                        {label: "Lojban", id: 48, color: "#81dcdf", textcolor: "#419c9f", size: 5, desc: "Language: <a href='http://en.wikipedia.org/wiki/Lojban_language' target='_blank'>Lojban</a><br/><br/>No similar languages found."},
                        {label: "Luxembourgish", id: 49, color: "#c7fa8b", textcolor: "#87ba4b", size: 6.4535, desc: "Language: <a href='http://en.wikipedia.org/wiki/Luxembourgish_language' target='_blank'>Luxembourgish</a><br/><br/>Similar to:<ul><li><a href='http://en.wikipedia.org/wiki/German_language' target='_blank'>German</a> at 46%</li><li><a href='http://en.wikipedia.org/wiki/Limburgish_language' target='_blank'>Limburgish</a> at 41%</li><li><a href='http://en.wikipedia.org/wiki/Swedish_language' target='_blank'>Swedish</a> at 38%</li><li><a href='http://en.wikipedia.org/wiki/Afrikaans_language' target='_blank'>Afrikaans</a> at 37%</li><li><a href='http://en.wikipedia.org/wiki/Vilamovian_language' target='_blank'>Vilamovian</a> at 35%</li><li><a href='http://en.wikipedia.org/wiki/Dutch_language' target='_blank'>Dutch</a> at 35%</li><li><a href='http://en.wikipedia.org/wiki/Old_High_German_language' target='_blank'>Old High German</a> at 34%</li></ul>"},
                        {label: "Macedonian", id: 50, color: "#e4c7fc", textcolor: "#a487bc", size: 5, desc: "Language: <a href='http://en.wikipedia.org/wiki/Macedonian_language' target='_blank'>Macedonian</a><br/><br/>Similar to:<ul><li><a href='http://en.wikipedia.org/wiki/Serbo_Croatian_language' target='_blank'>Serbo Croatian</a> at 82%</li><li><a href='http://en.wikipedia.org/wiki/Slovene_language' target='_blank'>Slovene</a> at 62%</li><li><a href='http://en.wikipedia.org/wiki/Russian_language' target='_blank'>Russian</a> at 61%</li><li><a href='http://en.wikipedia.org/wiki/Ukrainian_language' target='_blank'>Ukrainian</a> at 55%</li><li><a href='http://en.wikipedia.org/wiki/Bulgarian_language' target='_blank'>Bulgarian</a> at 55%</li><li><a href='http://en.wikipedia.org/wiki/Slovak_language' target='_blank'>Slovak</a> at 53%</li><li><a href='http://en.wikipedia.org/wiki/Polish_language' target='_blank'>Polish</a> at 46%</li><li><a href='http://en.wikipedia.org/wiki/Kashubian_language' target='_blank'>Kashubian</a> at 44%</li><li><a href='http://en.wikipedia.org/wiki/Czech_language' target='_blank'>Czech</a> at 36%</li><li><a href='http://en.wikipedia.org/wiki/Romanian_language' target='_blank'>Romanian</a> at 34%</li><li><a href='http://en.wikipedia.org/wiki/Spanish_language' target='_blank'>Spanish</a> at 33%</li></ul>"},
                        {label: "Malagasy", id: 51, color: "#f0d4f1", textcolor: "#b094b1", size: 5.353, desc: "Language: <a href='http://en.wikipedia.org/wiki/Malagasy_language' target='_blank'>Malagasy</a><br/><br/>No similar languages found."},
                        {label: "Malay", id: 52, color: "#9ee5c8", textcolor: "#5ea588", size: 5, desc: "Language: <a href='http://en.wikipedia.org/wiki/Malay_language' target='_blank'>Malay</a><br/><br/>Similar to:<ul><li><a href='http://en.wikipedia.org/wiki/Indonesian_language' target='_blank'>Indonesian</a> at 79%</li></ul>"},
                        {label: "Maltese", id: 53, color: "#86fbc8", textcolor: "#46bb88", size: 5, desc: "Language: <a href='http://en.wikipedia.org/wiki/Maltese_language' target='_blank'>Maltese</a><br/><br/>Similar to:<ul><li><a href='http://en.wikipedia.org/wiki/Spanish_language' target='_blank'>Spanish</a> at 41%</li><li><a href='http://en.wikipedia.org/wiki/Portuguese_language' target='_blank'>Portuguese</a> at 35%</li><li><a href='http://en.wikipedia.org/wiki/Serbo_Croatian_language' target='_blank'>Serbo Croatian</a> at 34%</li><li><a href='http://en.wikipedia.org/wiki/Romanian_language' target='_blank'>Romanian</a> at 33%</li></ul>"},
                        {label: "Manx", id: 54, color: "#d9dee7", textcolor: "#999ea7", size: 7.5805, desc: "Language: <a href='http://en.wikipedia.org/wiki/Manx_language' target='_blank'>Manx</a><br/><br/>Similar to:<ul><li><a href='http://en.wikipedia.org/wiki/Scottish_Gaelic_language' target='_blank'>Scottish Gaelic</a> at 36%</li></ul>"},
                        {label: "Mapudungun", id: 55, color: "#97d1ab", textcolor: "#57916b", size: 5, desc: "Language: <a href='http://en.wikipedia.org/wiki/Mapudungun_language' target='_blank'>Mapudungun</a><br/><br/>No similar languages found."},
                        {label: "Middle English", id: 56, color: "#def7a4", textcolor: "#9eb764", size: 5, desc: "Language: <a href='http://en.wikipedia.org/wiki/Middle_English_language' target='_blank'>Middle English</a><br/><br/>Similar to:<ul><li><a href='http://en.wikipedia.org/wiki/English_language' target='_blank'>English</a> at 41%</li><li><a href='http://en.wikipedia.org/wiki/Old_Saxon_language' target='_blank'>Old Saxon</a> at 35%</li><li><a href='http://en.wikipedia.org/wiki/Old_French_language' target='_blank'>Old French</a> at 35%</li><li><a href='http://en.wikipedia.org/wiki/Swedish_language' target='_blank'>Swedish</a> at 34%</li><li><a href='http://en.wikipedia.org/wiki/Scots_language' target='_blank'>Scots</a> at 34%</li><li><a href='http://en.wikipedia.org/wiki/Limburgish_language' target='_blank'>Limburgish</a> at 34%</li></ul>"},
                        {label: "Middle French", id: 57, color: "#d1a2f2", textcolor: "#9162b2", size: 5, desc: "Language: <a href='http://en.wikipedia.org/wiki/Middle_French_language' target='_blank'>Middle French</a><br/><br/>Similar to:<ul><li><a href='http://en.wikipedia.org/wiki/Old_French_language' target='_blank'>Old French</a> at 68%</li><li><a href='http://en.wikipedia.org/wiki/Anglo_Norman_language' target='_blank'>Anglo Norman</a> at 58%</li><li><a href='http://en.wikipedia.org/wiki/JÃ¨rriais_language' target='_blank'>JÃ¨rriais</a> at 55%</li><li><a href='http://en.wikipedia.org/wiki/GuernÃ©siais_language' target='_blank'>GuernÃ©siais</a> at 54%</li><li><a href='http://en.wikipedia.org/wiki/Spanish_language' target='_blank'>Spanish</a> at 48%</li><li><a href='http://en.wikipedia.org/wiki/Interlingua_language' target='_blank'>Interlingua</a> at 48%</li><li><a href='http://en.wikipedia.org/wiki/French_language' target='_blank'>French</a> at 46%</li><li><a href='http://en.wikipedia.org/wiki/Occitan_language' target='_blank'>Occitan</a> at 46%</li><li><a href='http://en.wikipedia.org/wiki/Catalan_language' target='_blank'>Catalan</a> at 42%</li><li><a href='http://en.wikipedia.org/wiki/Portuguese_language' target='_blank'>Portuguese</a> at 41%</li><li><a href='http://en.wikipedia.org/wiki/Ido_language' target='_blank'>Ido</a> at 38%</li><li><a href='http://en.wikipedia.org/wiki/Galician_language' target='_blank'>Galician</a> at 37%</li><li><a href='http://en.wikipedia.org/wiki/Romansch_language' target='_blank'>Romansch</a> at 36%</li><li><a href='http://en.wikipedia.org/wiki/Tarantino_language' target='_blank'>Tarantino</a> at 35%</li><li><a href='http://en.wikipedia.org/wiki/Asturian_language' target='_blank'>Asturian</a> at 35%</li><li><a href='http://en.wikipedia.org/wiki/Venetian_language' target='_blank'>Venetian</a> at 35%</li><li><a href='http://en.wikipedia.org/wiki/Haitian_Creole_language' target='_blank'>Haitian Creole</a> at 35%</li><li><a href='http://en.wikipedia.org/wiki/Romanian_language' target='_blank'>Romanian</a> at 35%</li><li><a href='http://en.wikipedia.org/wiki/English_language' target='_blank'>English</a> at 34%</li><li><a href='http://en.wikipedia.org/wiki/Dalmatian_language' target='_blank'>Dalmatian</a> at 33%</li></ul>"},
                        {label: "Navajo", id: 58, color: "#94dab5", textcolor: "#549a75", size: 5, desc: "Language: <a href='http://en.wikipedia.org/wiki/Navajo_language' target='_blank'>Navajo</a><br/><br/>No similar languages found."},
                        {label: "Neapolitan", id: 59, color: "#8f80f7", textcolor: "#4f40b7", size: 5, desc: "Language: <a href='http://en.wikipedia.org/wiki/Neapolitan_language' target='_blank'>Neapolitan</a><br/><br/>Similar to:<ul><li><a href='http://en.wikipedia.org/wiki/Tarantino_language' target='_blank'>Tarantino</a> at 50%</li><li><a href='http://en.wikipedia.org/wiki/Sicilian_language' target='_blank'>Sicilian</a> at 47%</li><li><a href='http://en.wikipedia.org/wiki/Interlingua_language' target='_blank'>Interlingua</a> at 44%</li><li><a href='http://en.wikipedia.org/wiki/Spanish_language' target='_blank'>Spanish</a> at 44%</li><li><a href='http://en.wikipedia.org/wiki/Romansch_language' target='_blank'>Romansch</a> at 39%</li><li><a href='http://en.wikipedia.org/wiki/Romanian_language' target='_blank'>Romanian</a> at 39%</li><li><a href='http://en.wikipedia.org/wiki/Venetian_language' target='_blank'>Venetian</a> at 39%</li><li><a href='http://en.wikipedia.org/wiki/Portuguese_language' target='_blank'>Portuguese</a> at 38%</li><li><a href='http://en.wikipedia.org/wiki/Occitan_language' target='_blank'>Occitan</a> at 36%</li><li><a href='http://en.wikipedia.org/wiki/Galician_language' target='_blank'>Galician</a> at 36%</li></ul>"},
                        {label: "Northern Sami", id: 60, color: "#dcc9a7", textcolor: "#9c8967", size: 5, desc: "Language: <a href='http://en.wikipedia.org/wiki/Northern_Sami_language' target='_blank'>Northern Sami</a><br/><br/>No similar languages found."},
                        {label: "Norwegian", id: 61, color: "#caabb1", textcolor: "#8a6b71", size: 5.0815, desc: "Language: <a href='http://en.wikipedia.org/wiki/Norwegian_language' target='_blank'>Norwegian</a><br/><br/>Similar to:<ul><li><a href='http://en.wikipedia.org/wiki/Swedish_language' target='_blank'>Swedish</a> at 59%</li><li><a href='http://en.wikipedia.org/wiki/Danish_language' target='_blank'>Danish</a> at 59%</li><li><a href='http://en.wikipedia.org/wiki/Limburgish_language' target='_blank'>Limburgish</a> at 45%</li><li><a href='http://en.wikipedia.org/wiki/Faroese_language' target='_blank'>Faroese</a> at 39%</li><li><a href='http://en.wikipedia.org/wiki/Old_Norse_language' target='_blank'>Old Norse</a> at 38%</li><li><a href='http://en.wikipedia.org/wiki/Afrikaans_language' target='_blank'>Afrikaans</a> at 37%</li><li><a href='http://en.wikipedia.org/wiki/German_language' target='_blank'>German</a> at 34%</li><li><a href='http://en.wikipedia.org/wiki/Icelandic_language' target='_blank'>Icelandic</a> at 33%</li><li><a href='http://en.wikipedia.org/wiki/Old_Saxon_language' target='_blank'>Old Saxon</a> at 33%</li></ul>"},
                        {label: "Norwegian BokmÃ¥l", id: 62, color: "#9dc7ef", textcolor: "#5d87af", size: 5, desc: "Language: <a href='http://en.wikipedia.org/wiki/Norwegian_BokmÃ¥l_language' target='_blank'>Norwegian BokmÃ¥l</a><br/><br/>Similar to:<ul><li><a href='http://en.wikipedia.org/wiki/Norwegian_Nynorsk_language' target='_blank'>Norwegian Nynorsk</a> at 82%</li><li><a href='http://en.wikipedia.org/wiki/Swedish_language' target='_blank'>Swedish</a> at 67%</li><li><a href='http://en.wikipedia.org/wiki/Danish_language' target='_blank'>Danish</a> at 46%</li><li><a href='http://en.wikipedia.org/wiki/Old_Norse_language' target='_blank'>Old Norse</a> at 38%</li></ul>"},
                        {label: "Norwegian Nynorsk", id: 63, color: "#9dbbef", textcolor: "#5d7baf", size: 5, desc: "Language: <a href='http://en.wikipedia.org/wiki/Norwegian_Nynorsk_language' target='_blank'>Norwegian Nynorsk</a><br/><br/>Similar to:<ul><li><a href='http://en.wikipedia.org/wiki/Norwegian_BokmÃ¥l_language' target='_blank'>Norwegian BokmÃ¥l</a> at 82%</li><li><a href='http://en.wikipedia.org/wiki/Swedish_language' target='_blank'>Swedish</a> at 63%</li><li><a href='http://en.wikipedia.org/wiki/Old_Norse_language' target='_blank'>Old Norse</a> at 49%</li><li><a href='http://en.wikipedia.org/wiki/Faroese_language' target='_blank'>Faroese</a> at 39%</li><li><a href='http://en.wikipedia.org/wiki/Danish_language' target='_blank'>Danish</a> at 39%</li><li><a href='http://en.wikipedia.org/wiki/Spanish_language' target='_blank'>Spanish</a> at 38%</li><li><a href='http://en.wikipedia.org/wiki/Serbo_Croatian_language' target='_blank'>Serbo Croatian</a> at 35%</li><li><a href='http://en.wikipedia.org/wiki/Old_Saxon_language' target='_blank'>Old Saxon</a> at 34%</li><li><a href='http://en.wikipedia.org/wiki/English_language' target='_blank'>English</a> at 33%</li></ul>"},
                        {label: "Occitan", id: 64, color: "#d2a0c2", textcolor: "#926082", size: 5, desc: "Language: <a href='http://en.wikipedia.org/wiki/Occitan_language' target='_blank'>Occitan</a><br/><br/>Similar to:<ul><li><a href='http://en.wikipedia.org/wiki/Spanish_language' target='_blank'>Spanish</a> at 59%</li><li><a href='http://en.wikipedia.org/wiki/Catalan_language' target='_blank'>Catalan</a> at 56%</li><li><a href='http://en.wikipedia.org/wiki/Interlingua_language' target='_blank'>Interlingua</a> at 56%</li><li><a href='http://en.wikipedia.org/wiki/Portuguese_language' target='_blank'>Portuguese</a> at 54%</li><li><a href='http://en.wikipedia.org/wiki/Galician_language' target='_blank'>Galician</a> at 52%</li><li><a href='http://en.wikipedia.org/wiki/Old_French_language' target='_blank'>Old French</a> at 50%</li><li><a href='http://en.wikipedia.org/wiki/Asturian_language' target='_blank'>Asturian</a> at 48%</li><li><a href='http://en.wikipedia.org/wiki/GuernÃ©siais_language' target='_blank'>GuernÃ©siais</a> at 48%</li><li><a href='http://en.wikipedia.org/wiki/Romansch_language' target='_blank'>Romansch</a> at 48%</li><li><a href='http://en.wikipedia.org/wiki/Venetian_language' target='_blank'>Venetian</a> at 47%</li><li><a href='http://en.wikipedia.org/wiki/Middle_French_language' target='_blank'>Middle French</a> at 46%</li><li><a href='http://en.wikipedia.org/wiki/Anglo_Norman_language' target='_blank'>Anglo Norman</a> at 45%</li><li><a href='http://en.wikipedia.org/wiki/Ido_language' target='_blank'>Ido</a> at 43%</li><li><a href='http://en.wikipedia.org/wiki/Dalmatian_language' target='_blank'>Dalmatian</a> at 43%</li><li><a href='http://en.wikipedia.org/wiki/Romanian_language' target='_blank'>Romanian</a> at 41%</li><li><a href='http://en.wikipedia.org/wiki/JÃ¨rriais_language' target='_blank'>JÃ¨rriais</a> at 41%</li><li><a href='http://en.wikipedia.org/wiki/Tarantino_language' target='_blank'>Tarantino</a> at 38%</li><li><a href='http://en.wikipedia.org/wiki/Sicilian_language' target='_blank'>Sicilian</a> at 38%</li><li><a href='http://en.wikipedia.org/wiki/Ladino_language' target='_blank'>Ladino</a> at 38%</li><li><a href='http://en.wikipedia.org/wiki/Aromanian_language' target='_blank'>Aromanian</a> at 36%</li><li><a href='http://en.wikipedia.org/wiki/Neapolitan_language' target='_blank'>Neapolitan</a> at 36%</li><li><a href='http://en.wikipedia.org/wiki/Haitian_Creole_language' target='_blank'>Haitian Creole</a> at 34%</li><li><a href='http://en.wikipedia.org/wiki/French_language' target='_blank'>French</a> at 34%</li></ul>"},
                        {label: "Old French", id: 65, color: "#d4bc97", textcolor: "#947c57", size: 5, desc: "Language: <a href='http://en.wikipedia.org/wiki/Old_French_language' target='_blank'>Old French</a><br/><br/>Similar to:<ul><li><a href='http://en.wikipedia.org/wiki/Anglo_Norman_language' target='_blank'>Anglo Norman</a> at 78%</li><li><a href='http://en.wikipedia.org/wiki/Middle_French_language' target='_blank'>Middle French</a> at 68%</li><li><a href='http://en.wikipedia.org/wiki/GuernÃ©siais_language' target='_blank'>GuernÃ©siais</a> at 56%</li><li><a href='http://en.wikipedia.org/wiki/JÃ¨rriais_language' target='_blank'>JÃ¨rriais</a> at 51%</li><li><a href='http://en.wikipedia.org/wiki/Occitan_language' target='_blank'>Occitan</a> at 50%</li><li><a href='http://en.wikipedia.org/wiki/Interlingua_language' target='_blank'>Interlingua</a> at 49%</li><li><a href='http://en.wikipedia.org/wiki/Spanish_language' target='_blank'>Spanish</a> at 47%</li><li><a href='http://en.wikipedia.org/wiki/Catalan_language' target='_blank'>Catalan</a> at 42%</li><li><a href='http://en.wikipedia.org/wiki/French_language' target='_blank'>French</a> at 42%</li><li><a href='http://en.wikipedia.org/wiki/Galician_language' target='_blank'>Galician</a> at 40%</li><li><a href='http://en.wikipedia.org/wiki/Portuguese_language' target='_blank'>Portuguese</a> at 40%</li><li><a href='http://en.wikipedia.org/wiki/Romansch_language' target='_blank'>Romansch</a> at 39%</li><li><a href='http://en.wikipedia.org/wiki/Ido_language' target='_blank'>Ido</a> at 38%</li><li><a href='http://en.wikipedia.org/wiki/Asturian_language' target='_blank'>Asturian</a> at 38%</li><li><a href='http://en.wikipedia.org/wiki/Dalmatian_language' target='_blank'>Dalmatian</a> at 37%</li><li><a href='http://en.wikipedia.org/wiki/Haitian_Creole_language' target='_blank'>Haitian Creole</a> at 36%</li><li><a href='http://en.wikipedia.org/wiki/Venetian_language' target='_blank'>Venetian</a> at 35%</li><li><a href='http://en.wikipedia.org/wiki/Middle_English_language' target='_blank'>Middle English</a> at 35%</li><li><a href='http://en.wikipedia.org/wiki/Romanian_language' target='_blank'>Romanian</a> at 35%</li><li><a href='http://en.wikipedia.org/wiki/Tarantino_language' target='_blank'>Tarantino</a> at 34%</li></ul>"},
                        {label: "Old High German", id: 66, color: "#e3b5a3", textcolor: "#a37563", size: 5, desc: "Language: <a href='http://en.wikipedia.org/wiki/Old_High_German_language' target='_blank'>Old High German</a><br/><br/>Similar to:<ul><li><a href='http://en.wikipedia.org/wiki/Old_Saxon_language' target='_blank'>Old Saxon</a> at 58%</li><li><a href='http://en.wikipedia.org/wiki/Swedish_language' target='_blank'>Swedish</a> at 39%</li><li><a href='http://en.wikipedia.org/wiki/Old_Norse_language' target='_blank'>Old Norse</a> at 36%</li><li><a href='http://en.wikipedia.org/wiki/German_language' target='_blank'>German</a> at 36%</li><li><a href='http://en.wikipedia.org/wiki/Luxembourgish_language' target='_blank'>Luxembourgish</a> at 34%</li><li><a href='http://en.wikipedia.org/wiki/Afrikaans_language' target='_blank'>Afrikaans</a> at 34%</li><li><a href='http://en.wikipedia.org/wiki/Vilamovian_language' target='_blank'>Vilamovian</a> at 33%</li></ul>"},
                        {label: "Old Irish", id: 67, color: "#efcdc3", textcolor: "#af8d83", size: 5, desc: "Language: <a href='http://en.wikipedia.org/wiki/Old_Irish_language' target='_blank'>Old Irish</a><br/><br/>Similar to:<ul><li><a href='http://en.wikipedia.org/wiki/Scottish_Gaelic_language' target='_blank'>Scottish Gaelic</a> at 57%</li><li><a href='http://en.wikipedia.org/wiki/Irish_language' target='_blank'>Irish</a> at 43%</li></ul>"},
                        {label: "Old Norse", id: 68, color: "#f79283", textcolor: "#b75243", size: 5, desc: "Language: <a href='http://en.wikipedia.org/wiki/Old_Norse_language' target='_blank'>Old Norse</a><br/><br/>Similar to:<ul><li><a href='http://en.wikipedia.org/wiki/Swedish_language' target='_blank'>Swedish</a> at 52%</li><li><a href='http://en.wikipedia.org/wiki/Faroese_language' target='_blank'>Faroese</a> at 50%</li><li><a href='http://en.wikipedia.org/wiki/Icelandic_language' target='_blank'>Icelandic</a> at 49%</li><li><a href='http://en.wikipedia.org/wiki/Norwegian_Nynorsk_language' target='_blank'>Norwegian Nynorsk</a> at 49%</li><li><a href='http://en.wikipedia.org/wiki/Old_Saxon_language' target='_blank'>Old Saxon</a> at 41%</li><li><a href='http://en.wikipedia.org/wiki/Norwegian_BokmÃ¥l_language' target='_blank'>Norwegian BokmÃ¥l</a> at 38%</li><li><a href='http://en.wikipedia.org/wiki/Norwegian_language' target='_blank'>Norwegian</a> at 38%</li><li><a href='http://en.wikipedia.org/wiki/Old_High_German_language' target='_blank'>Old High German</a> at 36%</li></ul>"},
                        {label: "Old Saxon", id: 69, color: "#bfaada", textcolor: "#7f6a9a", size: 5, desc: "Language: <a href='http://en.wikipedia.org/wiki/Old_Saxon_language' target='_blank'>Old Saxon</a><br/><br/>Similar to:<ul><li><a href='http://en.wikipedia.org/wiki/Old_High_German_language' target='_blank'>Old High German</a> at 58%</li><li><a href='http://en.wikipedia.org/wiki/Swedish_language' target='_blank'>Swedish</a> at 43%</li><li><a href='http://en.wikipedia.org/wiki/Old_Norse_language' target='_blank'>Old Norse</a> at 41%</li><li><a href='http://en.wikipedia.org/wiki/Middle_English_language' target='_blank'>Middle English</a> at 35%</li><li><a href='http://en.wikipedia.org/wiki/Norwegian_Nynorsk_language' target='_blank'>Norwegian Nynorsk</a> at 34%</li><li><a href='http://en.wikipedia.org/wiki/West_Frisian_language' target='_blank'>West Frisian</a> at 34%</li><li><a href='http://en.wikipedia.org/wiki/Faroese_language' target='_blank'>Faroese</a> at 33%</li><li><a href='http://en.wikipedia.org/wiki/Afrikaans_language' target='_blank'>Afrikaans</a> at 33%</li><li><a href='http://en.wikipedia.org/wiki/Norwegian_language' target='_blank'>Norwegian</a> at 33%</li></ul>"},
                        {label: "Ottoman Turkish", id: 70, color: "#bbf191", textcolor: "#7bb151", size: 5, desc: "Language: <a href='http://en.wikipedia.org/wiki/Ottoman_Turkish_language' target='_blank'>Ottoman Turkish</a><br/><br/>Similar to:<ul><li><a href='http://en.wikipedia.org/wiki/Azeri_language' target='_blank'>Azeri</a> at 44%</li><li><a href='http://en.wikipedia.org/wiki/Turkish_language' target='_blank'>Turkish</a> at 41%</li></ul>"},
                        {label: "Polish", id: 71, color: "#d68c9e", textcolor: "#964c5e", size: 9.311, desc: "Language: <a href='http://en.wikipedia.org/wiki/Polish_language' target='_blank'>Polish</a><br/><br/>Similar to:<ul><li><a href='http://en.wikipedia.org/wiki/Kashubian_language' target='_blank'>Kashubian</a> at 55%</li><li><a href='http://en.wikipedia.org/wiki/Serbo_Croatian_language' target='_blank'>Serbo Croatian</a> at 49%</li><li><a href='http://en.wikipedia.org/wiki/Russian_language' target='_blank'>Russian</a> at 47%</li><li><a href='http://en.wikipedia.org/wiki/Macedonian_language' target='_blank'>Macedonian</a> at 46%</li><li><a href='http://en.wikipedia.org/wiki/Czech_language' target='_blank'>Czech</a> at 45%</li><li><a href='http://en.wikipedia.org/wiki/Slovak_language' target='_blank'>Slovak</a> at 41%</li><li><a href='http://en.wikipedia.org/wiki/Slovene_language' target='_blank'>Slovene</a> at 40%</li><li><a href='http://en.wikipedia.org/wiki/Bulgarian_language' target='_blank'>Bulgarian</a> at 40%</li><li><a href='http://en.wikipedia.org/wiki/Ukrainian_language' target='_blank'>Ukrainian</a> at 39%</li><li><a href='http://en.wikipedia.org/wiki/Spanish_language' target='_blank'>Spanish</a> at 36%</li></ul>"},
                        {label: "Portuguese", id: 72, color: "#b7c7f7", textcolor: "#7787b7", size: 8.8395, desc: "Language: <a href='http://en.wikipedia.org/wiki/Portuguese_language' target='_blank'>Portuguese</a><br/><br/>Similar to:<ul><li><a href='http://en.wikipedia.org/wiki/Galician_language' target='_blank'>Galician</a> at 77%</li><li><a href='http://en.wikipedia.org/wiki/Spanish_language' target='_blank'>Spanish</a> at 75%</li><li><a href='http://en.wikipedia.org/wiki/Asturian_language' target='_blank'>Asturian</a> at 65%</li><li><a href='http://en.wikipedia.org/wiki/Catalan_language' target='_blank'>Catalan</a> at 62%</li><li><a href='http://en.wikipedia.org/wiki/Interlingua_language' target='_blank'>Interlingua</a> at 62%</li><li><a href='http://en.wikipedia.org/wiki/Occitan_language' target='_blank'>Occitan</a> at 54%</li><li><a href='http://en.wikipedia.org/wiki/Ido_language' target='_blank'>Ido</a> at 50%</li><li><a href='http://en.wikipedia.org/wiki/Ladino_language' target='_blank'>Ladino</a> at 46%</li><li><a href='http://en.wikipedia.org/wiki/Esperanto_language' target='_blank'>Esperanto</a> at 43%</li><li><a href='http://en.wikipedia.org/wiki/Venetian_language' target='_blank'>Venetian</a> at 42%</li><li><a href='http://en.wikipedia.org/wiki/Romanian_language' target='_blank'>Romanian</a> at 42%</li><li><a href='http://en.wikipedia.org/wiki/Italian_language' target='_blank'>Italian</a> at 41%</li><li><a href='http://en.wikipedia.org/wiki/Anglo_Norman_language' target='_blank'>Anglo Norman</a> at 41%</li><li><a href='http://en.wikipedia.org/wiki/Middle_French_language' target='_blank'>Middle French</a> at 41%</li><li><a href='http://en.wikipedia.org/wiki/Old_French_language' target='_blank'>Old French</a> at 40%</li><li><a href='http://en.wikipedia.org/wiki/French_language' target='_blank'>French</a> at 39%</li><li><a href='http://en.wikipedia.org/wiki/Neapolitan_language' target='_blank'>Neapolitan</a> at 38%</li><li><a href='http://en.wikipedia.org/wiki/English_language' target='_blank'>English</a> at 38%</li><li><a href='http://en.wikipedia.org/wiki/Dalmatian_language' target='_blank'>Dalmatian</a> at 38%</li><li><a href='http://en.wikipedia.org/wiki/Romansch_language' target='_blank'>Romansch</a> at 37%</li><li><a href='http://en.wikipedia.org/wiki/GuernÃ©siais_language' target='_blank'>GuernÃ©siais</a> at 37%</li><li><a href='http://en.wikipedia.org/wiki/Serbo_Croatian_language' target='_blank'>Serbo Croatian</a> at 37%</li><li><a href='http://en.wikipedia.org/wiki/Aromanian_language' target='_blank'>Aromanian</a> at 36%</li><li><a href='http://en.wikipedia.org/wiki/Maltese_language' target='_blank'>Maltese</a> at 35%</li><li><a href='http://en.wikipedia.org/wiki/JÃ¨rriais_language' target='_blank'>JÃ¨rriais</a> at 35%</li><li><a href='http://en.wikipedia.org/wiki/Latin_language' target='_blank'>Latin</a> at 33%</li><li><a href='http://en.wikipedia.org/wiki/Haitian_Creole_language' target='_blank'>Haitian Creole</a> at 33%</li><li><a href='http://en.wikipedia.org/wiki/Russian_language' target='_blank'>Russian</a> at 33%</li><li><a href='http://en.wikipedia.org/wiki/Swedish_language' target='_blank'>Swedish</a> at 33%</li><li><a href='http://en.wikipedia.org/wiki/Tarantino_language' target='_blank'>Tarantino</a> at 33%</li></ul>"},
                        {label: "Romanian", id: 73, color: "#e6de95", textcolor: "#a69e55", size: 9.453, desc: "Language: <a href='http://en.wikipedia.org/wiki/Romanian_language' target='_blank'>Romanian</a><br/><br/>Similar to:<ul><li><a href='http://en.wikipedia.org/wiki/Aromanian_language' target='_blank'>Aromanian</a> at 68%</li><li><a href='http://en.wikipedia.org/wiki/Interlingua_language' target='_blank'>Interlingua</a> at 49%</li><li><a href='http://en.wikipedia.org/wiki/Galician_language' target='_blank'>Galician</a> at 47%</li><li><a href='http://en.wikipedia.org/wiki/Spanish_language' target='_blank'>Spanish</a> at 46%</li><li><a href='http://en.wikipedia.org/wiki/Asturian_language' target='_blank'>Asturian</a> at 46%</li><li><a href='http://en.wikipedia.org/wiki/Catalan_language' target='_blank'>Catalan</a> at 45%</li><li><a href='http://en.wikipedia.org/wiki/Portuguese_language' target='_blank'>Portuguese</a> at 42%</li><li><a href='http://en.wikipedia.org/wiki/Occitan_language' target='_blank'>Occitan</a> at 41%</li><li><a href='http://en.wikipedia.org/wiki/Dalmatian_language' target='_blank'>Dalmatian</a> at 40%</li><li><a href='http://en.wikipedia.org/wiki/Neapolitan_language' target='_blank'>Neapolitan</a> at 39%</li><li><a href='http://en.wikipedia.org/wiki/Ido_language' target='_blank'>Ido</a> at 38%</li><li><a href='http://en.wikipedia.org/wiki/Serbo_Croatian_language' target='_blank'>Serbo Croatian</a> at 37%</li><li><a href='http://en.wikipedia.org/wiki/Esperanto_language' target='_blank'>Esperanto</a> at 36%</li><li><a href='http://en.wikipedia.org/wiki/Anglo_Norman_language' target='_blank'>Anglo Norman</a> at 35%</li><li><a href='http://en.wikipedia.org/wiki/Middle_French_language' target='_blank'>Middle French</a> at 35%</li><li><a href='http://en.wikipedia.org/wiki/Old_French_language' target='_blank'>Old French</a> at 35%</li><li><a href='http://en.wikipedia.org/wiki/Macedonian_language' target='_blank'>Macedonian</a> at 34%</li><li><a href='http://en.wikipedia.org/wiki/Italian_language' target='_blank'>Italian</a> at 33%</li><li><a href='http://en.wikipedia.org/wiki/Maltese_language' target='_blank'>Maltese</a> at 33%</li></ul>"},
                        {label: "Romansch", id: 74, color: "#c7d9f7", textcolor: "#8799b7", size: 5, desc: "Language: <a href='http://en.wikipedia.org/wiki/Romansch_language' target='_blank'>Romansch</a><br/><br/>Similar to:<ul><li><a href='http://en.wikipedia.org/wiki/Interlingua_language' target='_blank'>Interlingua</a> at 48%</li><li><a href='http://en.wikipedia.org/wiki/Occitan_language' target='_blank'>Occitan</a> at 48%</li><li><a href='http://en.wikipedia.org/wiki/Spanish_language' target='_blank'>Spanish</a> at 46%</li><li><a href='http://en.wikipedia.org/wiki/Galician_language' target='_blank'>Galician</a> at 44%</li><li><a href='http://en.wikipedia.org/wiki/Anglo_Norman_language' target='_blank'>Anglo Norman</a> at 43%</li><li><a href='http://en.wikipedia.org/wiki/Dalmatian_language' target='_blank'>Dalmatian</a> at 43%</li><li><a href='http://en.wikipedia.org/wiki/Catalan_language' target='_blank'>Catalan</a> at 42%</li><li><a href='http://en.wikipedia.org/wiki/Asturian_language' target='_blank'>Asturian</a> at 41%</li><li><a href='http://en.wikipedia.org/wiki/GuernÃ©siais_language' target='_blank'>GuernÃ©siais</a> at 39%</li><li><a href='http://en.wikipedia.org/wiki/Neapolitan_language' target='_blank'>Neapolitan</a> at 39%</li><li><a href='http://en.wikipedia.org/wiki/Old_French_language' target='_blank'>Old French</a> at 39%</li><li><a href='http://en.wikipedia.org/wiki/Venetian_language' target='_blank'>Venetian</a> at 39%</li><li><a href='http://en.wikipedia.org/wiki/Portuguese_language' target='_blank'>Portuguese</a> at 37%</li><li><a href='http://en.wikipedia.org/wiki/JÃ¨rriais_language' target='_blank'>JÃ¨rriais</a> at 37%</li><li><a href='http://en.wikipedia.org/wiki/Aromanian_language' target='_blank'>Aromanian</a> at 37%</li><li><a href='http://en.wikipedia.org/wiki/Ido_language' target='_blank'>Ido</a> at 36%</li><li><a href='http://en.wikipedia.org/wiki/Middle_French_language' target='_blank'>Middle French</a> at 36%</li><li><a href='http://en.wikipedia.org/wiki/Sicilian_language' target='_blank'>Sicilian</a> at 35%</li></ul>"},
                        {label: "Russian", id: 75, color: "#bfaac1", textcolor: "#7f6a81", size: 12.744, desc: "Language: <a href='http://en.wikipedia.org/wiki/Russian_language' target='_blank'>Russian</a><br/><br/>Similar to:<ul><li><a href='http://en.wikipedia.org/wiki/Macedonian_language' target='_blank'>Macedonian</a> at 61%</li><li><a href='http://en.wikipedia.org/wiki/Bulgarian_language' target='_blank'>Bulgarian</a> at 58%</li><li><a href='http://en.wikipedia.org/wiki/Serbo_Croatian_language' target='_blank'>Serbo Croatian</a> at 54%</li><li><a href='http://en.wikipedia.org/wiki/Polish_language' target='_blank'>Polish</a> at 47%</li><li><a href='http://en.wikipedia.org/wiki/Czech_language' target='_blank'>Czech</a> at 44%</li><li><a href='http://en.wikipedia.org/wiki/Kashubian_language' target='_blank'>Kashubian</a> at 41%</li><li><a href='http://en.wikipedia.org/wiki/Lithuanian_language' target='_blank'>Lithuanian</a> at 39%</li><li><a href='http://en.wikipedia.org/wiki/Ukrainian_language' target='_blank'>Ukrainian</a> at 38%</li><li><a href='http://en.wikipedia.org/wiki/Slovene_language' target='_blank'>Slovene</a> at 37%</li><li><a href='http://en.wikipedia.org/wiki/Latvian_language' target='_blank'>Latvian</a> at 36%</li><li><a href='http://en.wikipedia.org/wiki/Esperanto_language' target='_blank'>Esperanto</a> at 36%</li><li><a href='http://en.wikipedia.org/wiki/Crimean_Tatar_language' target='_blank'>Crimean Tatar</a> at 34%</li><li><a href='http://en.wikipedia.org/wiki/Galician_language' target='_blank'>Galician</a> at 34%</li><li><a href='http://en.wikipedia.org/wiki/Slovak_language' target='_blank'>Slovak</a> at 34%</li><li><a href='http://en.wikipedia.org/wiki/Ido_language' target='_blank'>Ido</a> at 33%</li><li><a href='http://en.wikipedia.org/wiki/Spanish_language' target='_blank'>Spanish</a> at 33%</li><li><a href='http://en.wikipedia.org/wiki/Catalan_language' target='_blank'>Catalan</a> at 33%</li><li><a href='http://en.wikipedia.org/wiki/Asturian_language' target='_blank'>Asturian</a> at 33%</li><li><a href='http://en.wikipedia.org/wiki/Estonian_language' target='_blank'>Estonian</a> at 33%</li><li><a href='http://en.wikipedia.org/wiki/Portuguese_language' target='_blank'>Portuguese</a> at 33%</li></ul>"},
                        {label: "Scots", id: 76, color: "#e3b3b0", textcolor: "#a37370", size: 5, desc: "Language: <a href='http://en.wikipedia.org/wiki/Scots_language' target='_blank'>Scots</a><br/><br/>Similar to:<ul><li><a href='http://en.wikipedia.org/wiki/Middle_English_language' target='_blank'>Middle English</a> at 34%</li><li><a href='http://en.wikipedia.org/wiki/Swedish_language' target='_blank'>Swedish</a> at 33%</li></ul>"},
                        {label: "Scottish Gaelic", id: 77, color: "#acb1df", textcolor: "#6c719f", size: 9.995, desc: "Language: <a href='http://en.wikipedia.org/wiki/Scottish_Gaelic_language' target='_blank'>Scottish Gaelic</a><br/><br/>Similar to:<ul><li><a href='http://en.wikipedia.org/wiki/Old_Irish_language' target='_blank'>Old Irish</a> at 57%</li><li><a href='http://en.wikipedia.org/wiki/Irish_language' target='_blank'>Irish</a> at 53%</li><li><a href='http://en.wikipedia.org/wiki/Manx_language' target='_blank'>Manx</a> at 36%</li></ul>"},
                        {label: "Serbo Croatian", id: 78, color: "#c6dd8f", textcolor: "#869d4f", size: 14.3405, desc: "Language: <a href='http://en.wikipedia.org/wiki/Serbo_Croatian_language' target='_blank'>Serbo Croatian</a><br/><br/>Similar to:<ul><li><a href='http://en.wikipedia.org/wiki/Macedonian_language' target='_blank'>Macedonian</a> at 82%</li><li><a href='http://en.wikipedia.org/wiki/Bulgarian_language' target='_blank'>Bulgarian</a> at 60%</li><li><a href='http://en.wikipedia.org/wiki/Russian_language' target='_blank'>Russian</a> at 54%</li><li><a href='http://en.wikipedia.org/wiki/Czech_language' target='_blank'>Czech</a> at 50%</li><li><a href='http://en.wikipedia.org/wiki/Polish_language' target='_blank'>Polish</a> at 49%</li><li><a href='http://en.wikipedia.org/wiki/Slovene_language' target='_blank'>Slovene</a> at 47%</li><li><a href='http://en.wikipedia.org/wiki/Kashubian_language' target='_blank'>Kashubian</a> at 46%</li><li><a href='http://en.wikipedia.org/wiki/Lithuanian_language' target='_blank'>Lithuanian</a> at 43%</li><li><a href='http://en.wikipedia.org/wiki/Esperanto_language' target='_blank'>Esperanto</a> at 40%</li><li><a href='http://en.wikipedia.org/wiki/Catalan_language' target='_blank'>Catalan</a> at 38%</li><li><a href='http://en.wikipedia.org/wiki/Latvian_language' target='_blank'>Latvian</a> at 38%</li><li><a href='http://en.wikipedia.org/wiki/Galician_language' target='_blank'>Galician</a> at 38%</li><li><a href='http://en.wikipedia.org/wiki/Ido_language' target='_blank'>Ido</a> at 38%</li><li><a href='http://en.wikipedia.org/wiki/Romanian_language' target='_blank'>Romanian</a> at 37%</li><li><a href='http://en.wikipedia.org/wiki/Portuguese_language' target='_blank'>Portuguese</a> at 37%</li><li><a href='http://en.wikipedia.org/wiki/Slovak_language' target='_blank'>Slovak</a> at 37%</li><li><a href='http://en.wikipedia.org/wiki/Asturian_language' target='_blank'>Asturian</a> at 36%</li><li><a href='http://en.wikipedia.org/wiki/Norwegian_Nynorsk_language' target='_blank'>Norwegian Nynorsk</a> at 35%</li><li><a href='http://en.wikipedia.org/wiki/Crimean_Tatar_language' target='_blank'>Crimean Tatar</a> at 35%</li><li><a href='http://en.wikipedia.org/wiki/Estonian_language' target='_blank'>Estonian</a> at 34%</li><li><a href='http://en.wikipedia.org/wiki/Maltese_language' target='_blank'>Maltese</a> at 34%</li><li><a href='http://en.wikipedia.org/wiki/Albanian_language' target='_blank'>Albanian</a> at 34%</li><li><a href='http://en.wikipedia.org/wiki/Spanish_language' target='_blank'>Spanish</a> at 33%</li><li><a href='http://en.wikipedia.org/wiki/Danish_language' target='_blank'>Danish</a> at 33%</li><li><a href='http://en.wikipedia.org/wiki/Interlingua_language' target='_blank'>Interlingua</a> at 33%</li><li><a href='http://en.wikipedia.org/wiki/Hungarian_language' target='_blank'>Hungarian</a> at 33%</li></ul>"},
                        {label: "Sicilian", id: 79, color: "#b8a0ec", textcolor: "#7860ac", size: 5, desc: "Language: <a href='http://en.wikipedia.org/wiki/Sicilian_language' target='_blank'>Sicilian</a><br/><br/>Similar to:<ul><li><a href='http://en.wikipedia.org/wiki/Interlingua_language' target='_blank'>Interlingua</a> at 47%</li><li><a href='http://en.wikipedia.org/wiki/Neapolitan_language' target='_blank'>Neapolitan</a> at 47%</li><li><a href='http://en.wikipedia.org/wiki/Tarantino_language' target='_blank'>Tarantino</a> at 46%</li><li><a href='http://en.wikipedia.org/wiki/Spanish_language' target='_blank'>Spanish</a> at 44%</li><li><a href='http://en.wikipedia.org/wiki/Venetian_language' target='_blank'>Venetian</a> at 41%</li><li><a href='http://en.wikipedia.org/wiki/Asturian_language' target='_blank'>Asturian</a> at 40%</li><li><a href='http://en.wikipedia.org/wiki/Occitan_language' target='_blank'>Occitan</a> at 38%</li><li><a href='http://en.wikipedia.org/wiki/Galician_language' target='_blank'>Galician</a> at 38%</li><li><a href='http://en.wikipedia.org/wiki/Aromanian_language' target='_blank'>Aromanian</a> at 36%</li><li><a href='http://en.wikipedia.org/wiki/Romansch_language' target='_blank'>Romansch</a> at 35%</li><li><a href='http://en.wikipedia.org/wiki/Catalan_language' target='_blank'>Catalan</a> at 35%</li><li><a href='http://en.wikipedia.org/wiki/Dalmatian_language' target='_blank'>Dalmatian</a> at 33%</li></ul>"},
                        {label: "Slovak", id: 80, color: "#c9cdec", textcolor: "#898dac", size: 5, desc: "Language: <a href='http://en.wikipedia.org/wiki/Slovak_language' target='_blank'>Slovak</a><br/><br/>Similar to:<ul><li><a href='http://en.wikipedia.org/wiki/Slovene_language' target='_blank'>Slovene</a> at 61%</li><li><a href='http://en.wikipedia.org/wiki/Czech_language' target='_blank'>Czech</a> at 57%</li><li><a href='http://en.wikipedia.org/wiki/Macedonian_language' target='_blank'>Macedonian</a> at 53%</li><li><a href='http://en.wikipedia.org/wiki/Kashubian_language' target='_blank'>Kashubian</a> at 49%</li><li><a href='http://en.wikipedia.org/wiki/Ukrainian_language' target='_blank'>Ukrainian</a> at 48%</li><li><a href='http://en.wikipedia.org/wiki/Polish_language' target='_blank'>Polish</a> at 41%</li><li><a href='http://en.wikipedia.org/wiki/Bulgarian_language' target='_blank'>Bulgarian</a> at 38%</li><li><a href='http://en.wikipedia.org/wiki/Serbo_Croatian_language' target='_blank'>Serbo Croatian</a> at 37%</li><li><a href='http://en.wikipedia.org/wiki/Russian_language' target='_blank'>Russian</a> at 34%</li></ul>"},
                        {label: "Slovene", id: 81, color: "#f7d580", textcolor: "#b79540", size: 5.3315, desc: "Language: <a href='http://en.wikipedia.org/wiki/Slovene_language' target='_blank'>Slovene</a><br/><br/>Similar to:<ul><li><a href='http://en.wikipedia.org/wiki/Macedonian_language' target='_blank'>Macedonian</a> at 62%</li><li><a href='http://en.wikipedia.org/wiki/Slovak_language' target='_blank'>Slovak</a> at 61%</li><li><a href='http://en.wikipedia.org/wiki/Serbo_Croatian_language' target='_blank'>Serbo Croatian</a> at 47%</li><li><a href='http://en.wikipedia.org/wiki/Bulgarian_language' target='_blank'>Bulgarian</a> at 44%</li><li><a href='http://en.wikipedia.org/wiki/Ukrainian_language' target='_blank'>Ukrainian</a> at 44%</li><li><a href='http://en.wikipedia.org/wiki/Czech_language' target='_blank'>Czech</a> at 42%</li><li><a href='http://en.wikipedia.org/wiki/Kashubian_language' target='_blank'>Kashubian</a> at 42%</li><li><a href='http://en.wikipedia.org/wiki/Polish_language' target='_blank'>Polish</a> at 40%</li><li><a href='http://en.wikipedia.org/wiki/Russian_language' target='_blank'>Russian</a> at 37%</li><li><a href='http://en.wikipedia.org/wiki/Lithuanian_language' target='_blank'>Lithuanian</a> at 36%</li></ul>"},
                        {label: "Spanish", id: 82, color: "#abe4e4", textcolor: "#6ba4a4", size: 16.4895, desc: "Language: <a href='http://en.wikipedia.org/wiki/Spanish_language' target='_blank'>Spanish</a><br/><br/>Similar to:<ul><li><a href='http://en.wikipedia.org/wiki/Galician_language' target='_blank'>Galician</a> at 84%</li><li><a href='http://en.wikipedia.org/wiki/Asturian_language' target='_blank'>Asturian</a> at 79%</li><li><a href='http://en.wikipedia.org/wiki/Portuguese_language' target='_blank'>Portuguese</a> at 75%</li><li><a href='http://en.wikipedia.org/wiki/Catalan_language' target='_blank'>Catalan</a> at 75%</li><li><a href='http://en.wikipedia.org/wiki/Interlingua_language' target='_blank'>Interlingua</a> at 69%</li><li><a href='http://en.wikipedia.org/wiki/Occitan_language' target='_blank'>Occitan</a> at 59%</li><li><a href='http://en.wikipedia.org/wiki/Ladino_language' target='_blank'>Ladino</a> at 57%</li><li><a href='http://en.wikipedia.org/wiki/Ido_language' target='_blank'>Ido</a> at 57%</li><li><a href='http://en.wikipedia.org/wiki/Italian_language' target='_blank'>Italian</a> at 54%</li><li><a href='http://en.wikipedia.org/wiki/French_language' target='_blank'>French</a> at 49%</li><li><a href='http://en.wikipedia.org/wiki/Esperanto_language' target='_blank'>Esperanto</a> at 48%</li><li><a href='http://en.wikipedia.org/wiki/Middle_French_language' target='_blank'>Middle French</a> at 48%</li><li><a href='http://en.wikipedia.org/wiki/Anglo_Norman_language' target='_blank'>Anglo Norman</a> at 47%</li><li><a href='http://en.wikipedia.org/wiki/Old_French_language' target='_blank'>Old French</a> at 47%</li><li><a href='http://en.wikipedia.org/wiki/Romanian_language' target='_blank'>Romanian</a> at 46%</li><li><a href='http://en.wikipedia.org/wiki/Romansch_language' target='_blank'>Romansch</a> at 46%</li><li><a href='http://en.wikipedia.org/wiki/Neapolitan_language' target='_blank'>Neapolitan</a> at 44%</li><li><a href='http://en.wikipedia.org/wiki/Dalmatian_language' target='_blank'>Dalmatian</a> at 44%</li><li><a href='http://en.wikipedia.org/wiki/Sicilian_language' target='_blank'>Sicilian</a> at 44%</li><li><a href='http://en.wikipedia.org/wiki/English_language' target='_blank'>English</a> at 43%</li><li><a href='http://en.wikipedia.org/wiki/Latin_language' target='_blank'>Latin</a> at 42%</li><li><a href='http://en.wikipedia.org/wiki/Maltese_language' target='_blank'>Maltese</a> at 41%</li><li><a href='http://en.wikipedia.org/wiki/GuernÃ©siais_language' target='_blank'>GuernÃ©siais</a> at 40%</li><li><a href='http://en.wikipedia.org/wiki/Aromanian_language' target='_blank'>Aromanian</a> at 40%</li><li><a href='http://en.wikipedia.org/wiki/Haitian_Creole_language' target='_blank'>Haitian Creole</a> at 38%</li><li><a href='http://en.wikipedia.org/wiki/Norwegian_Nynorsk_language' target='_blank'>Norwegian Nynorsk</a> at 38%</li><li><a href='http://en.wikipedia.org/wiki/Venetian_language' target='_blank'>Venetian</a> at 37%</li><li><a href='http://en.wikipedia.org/wiki/JÃ¨rriais_language' target='_blank'>JÃ¨rriais</a> at 37%</li><li><a href='http://en.wikipedia.org/wiki/Polish_language' target='_blank'>Polish</a> at 36%</li><li><a href='http://en.wikipedia.org/wiki/Hiligaynon_language' target='_blank'>Hiligaynon</a> at 35%</li><li><a href='http://en.wikipedia.org/wiki/Basque_language' target='_blank'>Basque</a> at 34%</li><li><a href='http://en.wikipedia.org/wiki/German_language' target='_blank'>German</a> at 34%</li><li><a href='http://en.wikipedia.org/wiki/Russian_language' target='_blank'>Russian</a> at 33%</li><li><a href='http://en.wikipedia.org/wiki/Macedonian_language' target='_blank'>Macedonian</a> at 33%</li><li><a href='http://en.wikipedia.org/wiki/Bulgarian_language' target='_blank'>Bulgarian</a> at 33%</li><li><a href='http://en.wikipedia.org/wiki/Danish_language' target='_blank'>Danish</a> at 33%</li><li><a href='http://en.wikipedia.org/wiki/Serbo_Croatian_language' target='_blank'>Serbo Croatian</a> at 33%</li></ul>"},
                        {label: "Swahili", id: 83, color: "#c7bfb1", textcolor: "#877f71", size: 5, desc: "Language: <a href='http://en.wikipedia.org/wiki/Swahili_language' target='_blank'>Swahili</a><br/><br/>No similar languages found."},
                        {label: "Swedish", id: 84, color: "#86dcb1", textcolor: "#469c71", size: 11.2835, desc: "Language: <a href='http://en.wikipedia.org/wiki/Swedish_language' target='_blank'>Swedish</a><br/><br/>Similar to:<ul><li><a href='http://en.wikipedia.org/wiki/Norwegian_BokmÃ¥l_language' target='_blank'>Norwegian BokmÃ¥l</a> at 67%</li><li><a href='http://en.wikipedia.org/wiki/Norwegian_Nynorsk_language' target='_blank'>Norwegian Nynorsk</a> at 63%</li><li><a href='http://en.wikipedia.org/wiki/Danish_language' target='_blank'>Danish</a> at 61%</li><li><a href='http://en.wikipedia.org/wiki/Norwegian_language' target='_blank'>Norwegian</a> at 59%</li><li><a href='http://en.wikipedia.org/wiki/Old_Norse_language' target='_blank'>Old Norse</a> at 52%</li><li><a href='http://en.wikipedia.org/wiki/Faroese_language' target='_blank'>Faroese</a> at 49%</li><li><a href='http://en.wikipedia.org/wiki/Limburgish_language' target='_blank'>Limburgish</a> at 45%</li><li><a href='http://en.wikipedia.org/wiki/Old_Saxon_language' target='_blank'>Old Saxon</a> at 43%</li><li><a href='http://en.wikipedia.org/wiki/Icelandic_language' target='_blank'>Icelandic</a> at 42%</li><li><a href='http://en.wikipedia.org/wiki/Afrikaans_language' target='_blank'>Afrikaans</a> at 41%</li><li><a href='http://en.wikipedia.org/wiki/German_language' target='_blank'>German</a> at 41%</li><li><a href='http://en.wikipedia.org/wiki/Old_High_German_language' target='_blank'>Old High German</a> at 39%</li><li><a href='http://en.wikipedia.org/wiki/Luxembourgish_language' target='_blank'>Luxembourgish</a> at 38%</li><li><a href='http://en.wikipedia.org/wiki/Dutch_language' target='_blank'>Dutch</a> at 37%</li><li><a href='http://en.wikipedia.org/wiki/Ido_language' target='_blank'>Ido</a> at 35%</li><li><a href='http://en.wikipedia.org/wiki/Catalan_language' target='_blank'>Catalan</a> at 35%</li><li><a href='http://en.wikipedia.org/wiki/Esperanto_language' target='_blank'>Esperanto</a> at 35%</li><li><a href='http://en.wikipedia.org/wiki/Middle_English_language' target='_blank'>Middle English</a> at 34%</li><li><a href='http://en.wikipedia.org/wiki/Galician_language' target='_blank'>Galician</a> at 34%</li><li><a href='http://en.wikipedia.org/wiki/Interlingua_language' target='_blank'>Interlingua</a> at 34%</li><li><a href='http://en.wikipedia.org/wiki/Asturian_language' target='_blank'>Asturian</a> at 33%</li><li><a href='http://en.wikipedia.org/wiki/Scots_language' target='_blank'>Scots</a> at 33%</li><li><a href='http://en.wikipedia.org/wiki/Estonian_language' target='_blank'>Estonian</a> at 33%</li><li><a href='http://en.wikipedia.org/wiki/Portuguese_language' target='_blank'>Portuguese</a> at 33%</li></ul>"},
                        {label: "Tagalog", id: 85, color: "#a7f4bb", textcolor: "#67b47b", size: 5, desc: "Language: <a href='http://en.wikipedia.org/wiki/Tagalog_language' target='_blank'>Tagalog</a><br/><br/>Similar to:<ul><li><a href='http://en.wikipedia.org/wiki/Hiligaynon_language' target='_blank'>Hiligaynon</a> at 46%</li></ul>"},
                        {label: "Tarantino", id: 86, color: "#d2849e", textcolor: "#92445e", size: 5, desc: "Language: <a href='http://en.wikipedia.org/wiki/Tarantino_language' target='_blank'>Tarantino</a><br/><br/>Similar to:<ul><li><a href='http://en.wikipedia.org/wiki/Neapolitan_language' target='_blank'>Neapolitan</a> at 50%</li><li><a href='http://en.wikipedia.org/wiki/Interlingua_language' target='_blank'>Interlingua</a> at 49%</li><li><a href='http://en.wikipedia.org/wiki/Venetian_language' target='_blank'>Venetian</a> at 46%</li><li><a href='http://en.wikipedia.org/wiki/Sicilian_language' target='_blank'>Sicilian</a> at 46%</li><li><a href='http://en.wikipedia.org/wiki/Galician_language' target='_blank'>Galician</a> at 39%</li><li><a href='http://en.wikipedia.org/wiki/Occitan_language' target='_blank'>Occitan</a> at 38%</li><li><a href='http://en.wikipedia.org/wiki/Anglo_Norman_language' target='_blank'>Anglo Norman</a> at 37%</li><li><a href='http://en.wikipedia.org/wiki/Asturian_language' target='_blank'>Asturian</a> at 37%</li><li><a href='http://en.wikipedia.org/wiki/JÃ¨rriais_language' target='_blank'>JÃ¨rriais</a> at 37%</li><li><a href='http://en.wikipedia.org/wiki/GuernÃ©siais_language' target='_blank'>GuernÃ©siais</a> at 36%</li><li><a href='http://en.wikipedia.org/wiki/Catalan_language' target='_blank'>Catalan</a> at 36%</li><li><a href='http://en.wikipedia.org/wiki/Middle_French_language' target='_blank'>Middle French</a> at 35%</li><li><a href='http://en.wikipedia.org/wiki/Old_French_language' target='_blank'>Old French</a> at 34%</li><li><a href='http://en.wikipedia.org/wiki/Portuguese_language' target='_blank'>Portuguese</a> at 33%</li></ul>"},
                        {label: "Tok Pisin", id: 87, color: "#d28bd7", textcolor: "#924b97", size: 5, desc: "Language: <a href='http://en.wikipedia.org/wiki/Tok_Pisin_language' target='_blank'>Tok Pisin</a><br/><br/>No similar languages found."},
                        {label: "Turkish", id: 88, color: "#ec8e97", textcolor: "#ac4e57", size: 8.4785, desc: "Language: <a href='http://en.wikipedia.org/wiki/Turkish_language' target='_blank'>Turkish</a><br/><br/>Similar to:<ul><li><a href='http://en.wikipedia.org/wiki/Azeri_language' target='_blank'>Azeri</a> at 61%</li><li><a href='http://en.wikipedia.org/wiki/Crimean_Tatar_language' target='_blank'>Crimean Tatar</a> at 51%</li><li><a href='http://en.wikipedia.org/wiki/Ottoman_Turkish_language' target='_blank'>Ottoman Turkish</a> at 41%</li></ul>"},
                        {label: "Ukrainian", id: 89, color: "#a9cc96", textcolor: "#698c56", size: 5, desc: "Language: <a href='http://en.wikipedia.org/wiki/Ukrainian_language' target='_blank'>Ukrainian</a><br/><br/>Similar to:<ul><li><a href='http://en.wikipedia.org/wiki/Macedonian_language' target='_blank'>Macedonian</a> at 55%</li><li><a href='http://en.wikipedia.org/wiki/Slovak_language' target='_blank'>Slovak</a> at 48%</li><li><a href='http://en.wikipedia.org/wiki/Kashubian_language' target='_blank'>Kashubian</a> at 44%</li><li><a href='http://en.wikipedia.org/wiki/Slovene_language' target='_blank'>Slovene</a> at 44%</li><li><a href='http://en.wikipedia.org/wiki/Bulgarian_language' target='_blank'>Bulgarian</a> at 41%</li><li><a href='http://en.wikipedia.org/wiki/Polish_language' target='_blank'>Polish</a> at 39%</li><li><a href='http://en.wikipedia.org/wiki/Russian_language' target='_blank'>Russian</a> at 38%</li><li><a href='http://en.wikipedia.org/wiki/Czech_language' target='_blank'>Czech</a> at 34%</li></ul>"},
                        {label: "Venetian", id: 90, color: "#96f0d1", textcolor: "#56b091", size: 5.292, desc: "Language: <a href='http://en.wikipedia.org/wiki/Venetian_language' target='_blank'>Venetian</a><br/><br/>Similar to:<ul><li><a href='http://en.wikipedia.org/wiki/Interlingua_language' target='_blank'>Interlingua</a> at 53%</li><li><a href='http://en.wikipedia.org/wiki/Galician_language' target='_blank'>Galician</a> at 49%</li><li><a href='http://en.wikipedia.org/wiki/Occitan_language' target='_blank'>Occitan</a> at 47%</li><li><a href='http://en.wikipedia.org/wiki/Tarantino_language' target='_blank'>Tarantino</a> at 46%</li><li><a href='http://en.wikipedia.org/wiki/Asturian_language' target='_blank'>Asturian</a> at 45%</li><li><a href='http://en.wikipedia.org/wiki/Catalan_language' target='_blank'>Catalan</a> at 44%</li><li><a href='http://en.wikipedia.org/wiki/Portuguese_language' target='_blank'>Portuguese</a> at 42%</li><li><a href='http://en.wikipedia.org/wiki/Ido_language' target='_blank'>Ido</a> at 41%</li><li><a href='http://en.wikipedia.org/wiki/Sicilian_language' target='_blank'>Sicilian</a> at 41%</li><li><a href='http://en.wikipedia.org/wiki/Dalmatian_language' target='_blank'>Dalmatian</a> at 39%</li><li><a href='http://en.wikipedia.org/wiki/Neapolitan_language' target='_blank'>Neapolitan</a> at 39%</li><li><a href='http://en.wikipedia.org/wiki/Ladino_language' target='_blank'>Ladino</a> at 39%</li><li><a href='http://en.wikipedia.org/wiki/Romansch_language' target='_blank'>Romansch</a> at 39%</li><li><a href='http://en.wikipedia.org/wiki/Spanish_language' target='_blank'>Spanish</a> at 37%</li><li><a href='http://en.wikipedia.org/wiki/Anglo_Norman_language' target='_blank'>Anglo Norman</a> at 36%</li><li><a href='http://en.wikipedia.org/wiki/GuernÃ©siais_language' target='_blank'>GuernÃ©siais</a> at 36%</li><li><a href='http://en.wikipedia.org/wiki/Italian_language' target='_blank'>Italian</a> at 35%</li><li><a href='http://en.wikipedia.org/wiki/Old_French_language' target='_blank'>Old French</a> at 35%</li><li><a href='http://en.wikipedia.org/wiki/Middle_French_language' target='_blank'>Middle French</a> at 35%</li><li><a href='http://en.wikipedia.org/wiki/Aromanian_language' target='_blank'>Aromanian</a> at 34%</li><li><a href='http://en.wikipedia.org/wiki/Esperanto_language' target='_blank'>Esperanto</a> at 33%</li></ul>"},
                        {label: "Vilamovian", id: 91, color: "#ebc3d4", textcolor: "#ab8394", size: 5, desc: "Language: <a href='http://en.wikipedia.org/wiki/Vilamovian_language' target='_blank'>Vilamovian</a><br/><br/>Similar to:<ul><li><a href='http://en.wikipedia.org/wiki/Luxembourgish_language' target='_blank'>Luxembourgish</a> at 35%</li><li><a href='http://en.wikipedia.org/wiki/Yiddish_language' target='_blank'>Yiddish</a> at 34%</li><li><a href='http://en.wikipedia.org/wiki/Old_High_German_language' target='_blank'>Old High German</a> at 33%</li></ul>"},
                        {label: "VolapÃ¼k", id: 92, color: "#a291d1", textcolor: "#625191", size: 5, desc: "Language: <a href='http://en.wikipedia.org/wiki/VolapÃ¼k_language' target='_blank'>VolapÃ¼k</a><br/><br/>No similar languages found."},
                        {label: "Welsh", id: 93, color: "#adab86", textcolor: "#6d6b46", size: 5, desc: "Language: <a href='http://en.wikipedia.org/wiki/Welsh_language' target='_blank'>Welsh</a><br/><br/>Similar to:<ul><li><a href='http://en.wikipedia.org/wiki/Cornish_language' target='_blank'>Cornish</a> at 48%</li><li><a href='http://en.wikipedia.org/wiki/Breton_language' target='_blank'>Breton</a> at 42%</li></ul>"},
                        {label: "West Frisian", id: 94, color: "#b388ac", textcolor: "#73486c", size: 5, desc: "Language: <a href='http://en.wikipedia.org/wiki/West_Frisian_language' target='_blank'>West Frisian</a><br/><br/>Similar to:<ul><li><a href='http://en.wikipedia.org/wiki/Limburgish_language' target='_blank'>Limburgish</a> at 47%</li><li><a href='http://en.wikipedia.org/wiki/Afrikaans_language' target='_blank'>Afrikaans</a> at 45%</li><li><a href='http://en.wikipedia.org/wiki/Old_Saxon_language' target='_blank'>Old Saxon</a> at 34%</li></ul>"},
                        {label: "Yiddish", id: 95, color: "#92cddb", textcolor: "#528d9b", size: 5, desc: "Language: <a href='http://en.wikipedia.org/wiki/Yiddish_language' target='_blank'>Yiddish</a><br/><br/>Similar to:<ul><li><a href='http://en.wikipedia.org/wiki/Vilamovian_language' target='_blank'>Vilamovian</a> at 34%</li></ul>"}
                    ];

                    var nodesHash = [];
                    nodesHash["Adyghe"] = 0;
                    nodesHash["Afrikaans"] = 1;
                    nodesHash["Albanian"] = 2;
                    nodesHash["Anglo Norman"] = 3;
                    nodesHash["Aromanian"] = 4;
                    nodesHash["Asturian"] = 5;
                    nodesHash["Azeri"] = 6;
                    nodesHash["Basque"] = 7;
                    nodesHash["Breton"] = 8;
                    nodesHash["Bulgarian"] = 9;
                    nodesHash["Catalan"] = 10;
                    nodesHash["Chamicuro"] = 11;
                    nodesHash["Classical Nahuatl"] = 12;
                    nodesHash["Cornish"] = 13;
                    nodesHash["Crimean Tatar"] = 14;
                    nodesHash["Czech"] = 15;
                    nodesHash["Dalmatian"] = 16;
                    nodesHash["Danish"] = 17;
                    nodesHash["Dutch"] = 18;
                    nodesHash["English"] = 19;
                    nodesHash["Esperanto"] = 20;
                    nodesHash["Estonian"] = 21;
                    nodesHash["Faroese"] = 22;
                    nodesHash["Finnish"] = 23;
                    nodesHash["French"] = 24;
                    nodesHash["Galician"] = 25;
                    nodesHash["German"] = 26;
                    nodesHash["Greek"] = 27;
                    nodesHash["Greenlandic"] = 28;
                    nodesHash["GuernÃ©siais"] = 29;
                    nodesHash["Haitian Creole"] = 30;
                    nodesHash["Hawaiian"] = 31;
                    nodesHash["Hiligaynon"] = 32;
                    nodesHash["Hungarian"] = 33;
                    nodesHash["Icelandic"] = 34;
                    nodesHash["Ido"] = 35;
                    nodesHash["Indonesian"] = 36;
                    nodesHash["Interlingua"] = 37;
                    nodesHash["Irish"] = 38;
                    nodesHash["Italian"] = 39;
                    nodesHash["JÃ¨rriais"] = 40;
                    nodesHash["Kashubian"] = 41;
                    nodesHash["Kurdish"] = 42;
                    nodesHash["Ladino"] = 43;
                    nodesHash["Latin"] = 44;
                    nodesHash["Latvian"] = 45;
                    nodesHash["Limburgish"] = 46;
                    nodesHash["Lithuanian"] = 47;
                    nodesHash["Lojban"] = 48;
                    nodesHash["Luxembourgish"] = 49;
                    nodesHash["Macedonian"] = 50;
                    nodesHash["Malagasy"] = 51;
                    nodesHash["Malay"] = 52;
                    nodesHash["Maltese"] = 53;
                    nodesHash["Manx"] = 54;
                    nodesHash["Mapudungun"] = 55;
                    nodesHash["Middle English"] = 56;
                    nodesHash["Middle French"] = 57;
                    nodesHash["Navajo"] = 58;
                    nodesHash["Neapolitan"] = 59;
                    nodesHash["Northern Sami"] = 60;
                    nodesHash["Norwegian"] = 61;
                    nodesHash["Norwegian BokmÃ¥l"] = 62;
                    nodesHash["Norwegian Nynorsk"] = 63;
                    nodesHash["Occitan"] = 64;
                    nodesHash["Old French"] = 65;
                    nodesHash["Old High German"] = 66;
                    nodesHash["Old Irish"] = 67;
                    nodesHash["Old Norse"] = 68;
                    nodesHash["Old Saxon"] = 69;
                    nodesHash["Ottoman Turkish"] = 70;
                    nodesHash["Polish"] = 71;
                    nodesHash["Portuguese"] = 72;
                    nodesHash["Romanian"] = 73;
                    nodesHash["Romansch"] = 74;
                    nodesHash["Russian"] = 75;
                    nodesHash["Scots"] = 76;
                    nodesHash["Scottish Gaelic"] = 77;
                    nodesHash["Serbo Croatian"] = 78;
                    nodesHash["Sicilian"] = 79;
                    nodesHash["Slovak"] = 80;
                    nodesHash["Slovene"] = 81;
                    nodesHash["Spanish"] = 82;
                    nodesHash["Swahili"] = 83;
                    nodesHash["Swedish"] = 84;
                    nodesHash["Tagalog"] = 85;
                    nodesHash["Tarantino"] = 86;
                    nodesHash["Tok Pisin"] = 87;
                    nodesHash["Turkish"] = 88;
                    nodesHash["Ukrainian"] = 89;
                    nodesHash["Venetian"] = 90;
                    nodesHash["Vilamovian"] = 91;
                    nodesHash["VolapÃ¼k"] = 92;
                    nodesHash["Welsh"] = 93;
                    nodesHash["West Frisian"] = 94;
                    nodesHash["Yiddish"] = 95;

                    var linksArray = [
                        {desc: "Middle French -- French", source: 57, target: 24, weight: 0.463352032318284, color: "#cfcfff"},
                        {desc: "Middle French -- Ido", source: 57, target: 35, weight: 0.383321439278753, color: "#e3e3ff"},
                        {desc: "Middle French -- Romansch", source: 57, target: 74, weight: 0.362946535310438, color: "#e8e8ff"},
                        {desc: "Middle French -- Portuguese", source: 57, target: 72, weight: 0.411126216081552, color: "#dcdcff"},
                        {desc: "Middle French -- Catalan", source: 57, target: 10, weight: 0.424951754457142, color: "#d9d9ff"},
                        {desc: "Middle French -- Asturian", source: 57, target: 5, weight: 0.358784190429154, color: "#e9e9ff"},
                        {desc: "Middle French -- Venetian", source: 57, target: 90, weight: 0.357063027870304, color: "#e9e9ff"},
                        {desc: "Middle French -- Tarantino", source: 57, target: 86, weight: 0.359149758304868, color: "#e9e9ff"},
                        {desc: "Middle French -- English", source: 57, target: 19, weight: 0.342103372309722, color: "#ededff"},
                        {desc: "Middle French -- Interlingua", source: 57, target: 37, weight: 0.480498232927762, color: "#cbcbff"},
                        {desc: "Middle French -- Anglo Norman", source: 57, target: 3, weight: 0.582230987488062, color: "#b3b3ff"},
                        {desc: "Middle French -- GuernÃ©siais", source: 57, target: 29, weight: 0.544639134176687, color: "#bcbcff"},
                        {desc: "Middle French -- Haitian Creole", source: 57, target: 30, weight: 0.355952757961896, color: "#e9e9ff"},
                        {desc: "Middle French -- JÃ¨rriais", source: 57, target: 40, weight: 0.553185966164393, color: "#babaff"},
                        {desc: "Middle French -- Old French", source: 57, target: 65, weight: 0.684145679444443, color: "#9b9bff"},
                        {desc: "Middle French -- Galician", source: 57, target: 25, weight: 0.372313932611161, color: "#e5e5ff"},
                        {desc: "Middle French -- Dalmatian", source: 57, target: 16, weight: 0.337841261576902, color: "#eeeeff"},
                        {desc: "Middle French -- Occitan", source: 57, target: 64, weight: 0.462125933605786, color: "#d0d0ff"},
                        {desc: "Middle French -- Romanian", source: 57, target: 73, weight: 0.352874670927322, color: "#eaeaff"},
                        {desc: "Middle French -- Spanish", source: 57, target: 82, weight: 0.486472015453226, color: "#cacaff"},
                        {desc: "Ladino -- Interlingua", source: 43, target: 37, weight: 0.431935419900375, color: "#d7d7ff"},
                        {desc: "Ladino -- Latin", source: 43, target: 44, weight: 0.33315187070235, color: "#efefff"},
                        {desc: "Ladino -- Ido", source: 43, target: 35, weight: 0.367096281271376, color: "#e7e7ff"},
                        {desc: "Ladino -- Catalan", source: 43, target: 10, weight: 0.378386121285475, color: "#e4e4ff"},
                        {desc: "Ladino -- Portuguese", source: 43, target: 72, weight: 0.468473742779661, color: "#ceceff"},
                        {desc: "Ladino -- Asturian", source: 43, target: 5, weight: 0.478019827903103, color: "#ccccff"},
                        {desc: "Ladino -- Galician", source: 43, target: 25, weight: 0.477221710976465, color: "#ccccff"},
                        {desc: "Ladino -- Occitan", source: 43, target: 64, weight: 0.381633242879595, color: "#e3e3ff"},
                        {desc: "Ladino -- Venetian", source: 43, target: 90, weight: 0.392004989400631, color: "#e1e1ff"},
                        {desc: "Ladino -- Spanish", source: 43, target: 82, weight: 0.57732095007192, color: "#b4b4ff"},
                        {desc: "Middle English -- English", source: 56, target: 19, weight: 0.41272955003796, color: "#dcdcff"},
                        {desc: "Middle English -- Old Saxon", source: 56, target: 69, weight: 0.35859030517903, color: "#e9e9ff"},
                        {desc: "Middle English -- Old French", source: 56, target: 65, weight: 0.356594703099833, color: "#e9e9ff"},
                        {desc: "Middle English -- Swedish", source: 56, target: 84, weight: 0.349682158597595, color: "#ebebff"},
                        {desc: "Middle English -- Scots", source: 56, target: 76, weight: 0.346856789640438, color: "#ebebff"},
                        {desc: "Middle English -- Limburgish", source: 56, target: 46, weight: 0.341223742176847, color: "#ededff"},
                        {desc: "French -- Middle French", source: 24, target: 57, weight: 0.463352032318284, color: "#cfcfff"},
                        {desc: "French -- Latin", source: 24, target: 44, weight: 0.388929238314212, color: "#e1e1ff"},
                        {desc: "French -- Ido", source: 24, target: 35, weight: 0.334836264694288, color: "#eeeeff"},
                        {desc: "French -- Portuguese", source: 24, target: 72, weight: 0.391336320901031, color: "#e1e1ff"},
                        {desc: "French -- Catalan", source: 24, target: 10, weight: 0.609209470925079, color: "#acacff"},
                        {desc: "French -- Asturian", source: 24, target: 5, weight: 0.52133095824163, color: "#c2c2ff"},
                        {desc: "French -- Esperanto", source: 24, target: 20, weight: 0.442857168347579, color: "#d4d4ff"},
                        {desc: "French -- Danish", source: 24, target: 17, weight: 0.365774760105095, color: "#e7e7ff"},
                        {desc: "French -- Aromanian", source: 24, target: 4, weight: 0.372909733105896, color: "#e5e5ff"},
                        {desc: "French -- English", source: 24, target: 19, weight: 0.459346389136046, color: "#d0d0ff"},
                        {desc: "French -- Interlingua", source: 24, target: 37, weight: 0.351188695250526, color: "#eaeaff"},
                        {desc: "French -- Albanian", source: 24, target: 2, weight: 0.332638436769228, color: "#efefff"},
                        {desc: "French -- Dutch", source: 24, target: 18, weight: 0.350681910934928, color: "#ebebff"},
                        {desc: "French -- Anglo Norman", source: 24, target: 3, weight: 0.666730596049357, color: "#9f9fff"},
                        {desc: "French -- GuernÃ©siais", source: 24, target: 29, weight: 0.367878310516009, color: "#e6e6ff"},
                        {desc: "French -- JÃ¨rriais", source: 24, target: 40, weight: 0.409793110774757, color: "#dcdcff"},
                        {desc: "French -- German", source: 24, target: 26, weight: 0.364921286007301, color: "#e7e7ff"},
                        {desc: "French -- Old French", source: 24, target: 65, weight: 0.428117130845519, color: "#d8d8ff"},
                        {desc: "French -- Occitan", source: 24, target: 64, weight: 0.340494520240559, color: "#ededff"},
                        {desc: "French -- Dalmatian", source: 24, target: 16, weight: 0.412414070610273, color: "#dcdcff"},
                        {desc: "French -- Galician", source: 24, target: 25, weight: 0.37495786407362, color: "#e5e5ff"},
                        {desc: "French -- Italian", source: 24, target: 39, weight: 0.554104578180194, color: "#babaff"},
                        {desc: "French -- Spanish", source: 24, target: 82, weight: 0.493667332955786, color: "#c8c8ff"},
                        {desc: "Ido -- Middle French", source: 35, target: 57, weight: 0.383321439278753, color: "#e3e3ff"},
                        {desc: "Ido -- Ladino", source: 35, target: 43, weight: 0.367096281271376, color: "#e7e7ff"},
                        {desc: "Ido -- Russian", source: 35, target: 75, weight: 0.339401317087572, color: "#ededff"},
                        {desc: "Ido -- Latin", source: 35, target: 44, weight: 0.444447107454564, color: "#d4d4ff"},
                        {desc: "Ido -- French", source: 35, target: 24, weight: 0.334836264694288, color: "#eeeeff"},
                        {desc: "Ido -- Romansch", source: 35, target: 74, weight: 0.368762129951607, color: "#e6e6ff"},
                        {desc: "Ido -- Portuguese", source: 35, target: 72, weight: 0.503669338965718, color: "#c6c6ff"},
                        {desc: "Ido -- Catalan", source: 35, target: 10, weight: 0.443372228390196, color: "#d4d4ff"},
                        {desc: "Ido -- Asturian", source: 35, target: 5, weight: 0.428133906075468, color: "#d8d8ff"},
                        {desc: "Ido -- Swedish", source: 35, target: 84, weight: 0.357655373188644, color: "#e9e9ff"},
                        {desc: "Ido -- Serbo Croatian", source: 35, target: 78, weight: 0.382975071438491, color: "#e3e3ff"},
                        {desc: "Ido -- Venetian", source: 35, target: 90, weight: 0.416255473258883, color: "#dbdbff"},
                        {desc: "Ido -- Esperanto", source: 35, target: 20, weight: 0.660113246441814, color: "#a0a0ff"},
                        {desc: "Ido -- English", source: 35, target: 19, weight: 0.426156910108239, color: "#d8d8ff"},
                        {desc: "Ido -- Interlingua", source: 35, target: 37, weight: 0.505173275829293, color: "#c5c5ff"},
                        {desc: "Ido -- Anglo Norman", source: 35, target: 3, weight: 0.357167916708305, color: "#e9e9ff"},
                        {desc: "Ido -- GuernÃ©siais", source: 35, target: 29, weight: 0.336143608715223, color: "#eeeeff"},
                        {desc: "Ido -- JÃ¨rriais", source: 35, target: 40, weight: 0.349235473800027, color: "#ebebff"},
                        {desc: "Ido -- Old French", source: 35, target: 65, weight: 0.386890941525547, color: "#e2e2ff"},
                        {desc: "Ido -- Galician", source: 35, target: 25, weight: 0.487016821119835, color: "#cacaff"},
                        {desc: "Ido -- Occitan", source: 35, target: 64, weight: 0.437418594504848, color: "#d6d6ff"},
                        {desc: "Ido -- Romanian", source: 35, target: 73, weight: 0.385355163911346, color: "#e2e2ff"},
                        {desc: "Ido -- Italian", source: 35, target: 39, weight: 0.587809317626038, color: "#b2b2ff"},
                        {desc: "Ido -- Spanish", source: 35, target: 82, weight: 0.572000386361266, color: "#b5b5ff"},
                        {desc: "Hiligaynon -- Tagalog", source: 32, target: 85, weight: 0.461845493548876, color: "#d0d0ff"},
                        {desc: "Hiligaynon -- Spanish", source: 32, target: 82, weight: 0.358396669128018, color: "#e9e9ff"},
                        {desc: "Lithuanian -- Russian", source: 47, target: 75, weight: 0.391329256845463, color: "#e1e1ff"},
                        {desc: "Lithuanian -- Latvian", source: 47, target: 45, weight: 0.466696251144178, color: "#cfcfff"},
                        {desc: "Lithuanian -- Serbo Croatian", source: 47, target: 78, weight: 0.433665258850984, color: "#d7d7ff"},
                        {desc: "Lithuanian -- Slovene", source: 47, target: 81, weight: 0.367128171360748, color: "#e7e7ff"},
                        {desc: "Old High German -- Vilamovian", source: 66, target: 91, weight: 0.337488687717853, color: "#eeeeff"},
                        {desc: "Old High German -- Afrikaans", source: 66, target: 1, weight: 0.348744383454323, color: "#ebebff"},
                        {desc: "Old High German -- Old Norse", source: 66, target: 68, weight: 0.365735771509022, color: "#e7e7ff"},
                        {desc: "Old High German -- Old Saxon", source: 66, target: 69, weight: 0.580007230454361, color: "#b3b3ff"},
                        {desc: "Old High German -- German", source: 66, target: 26, weight: 0.362626327765679, color: "#e8e8ff"},
                        {desc: "Old High German -- Luxembourgish", source: 66, target: 49, weight: 0.349988322574776, color: "#ebebff"},
                        {desc: "Old High German -- Swedish", source: 66, target: 84, weight: 0.392732168362588, color: "#e0e0ff"},
                        {desc: "Venetian -- Middle French", source: 90, target: 57, weight: 0.357063027870304, color: "#e9e9ff"},
                        {desc: "Venetian -- Ladino", source: 90, target: 43, weight: 0.392004989400631, color: "#e1e1ff"},
                        {desc: "Venetian -- Ido", source: 90, target: 35, weight: 0.416255473258883, color: "#dbdbff"},
                        {desc: "Venetian -- Romansch", source: 90, target: 74, weight: 0.390569955235945, color: "#e1e1ff"},
                        {desc: "Venetian -- Portuguese", source: 90, target: 72, weight: 0.429528632383963, color: "#d8d8ff"},
                        {desc: "Venetian -- Catalan", source: 90, target: 10, weight: 0.447147775091704, color: "#d3d3ff"},
                        {desc: "Venetian -- Asturian", source: 90, target: 5, weight: 0.452306314130847, color: "#d2d2ff"},
                        {desc: "Venetian -- Tarantino", source: 90, target: 86, weight: 0.461034078739191, color: "#d0d0ff"},
                        {desc: "Venetian -- Esperanto", source: 90, target: 20, weight: 0.332061866781988, color: "#efefff"},
                        {desc: "Venetian -- Aromanian", source: 90, target: 4, weight: 0.347675863942952, color: "#ebebff"},
                        {desc: "Venetian -- Interlingua", source: 90, target: 37, weight: 0.533577602499953, color: "#bfbfff"},
                        {desc: "Venetian -- Sicilian", source: 90, target: 79, weight: 0.410283904529753, color: "#dcdcff"},
                        {desc: "Venetian -- Anglo Norman", source: 90, target: 3, weight: 0.367190446729628, color: "#e7e7ff"},
                        {desc: "Venetian -- GuernÃ©siais", source: 90, target: 29, weight: 0.361223548195049, color: "#e8e8ff"},
                        {desc: "Venetian -- Neapolitan", source: 90, target: 59, weight: 0.392192127316762, color: "#e1e1ff"},
                        {desc: "Venetian -- Old French", source: 90, target: 65, weight: 0.357574734792802, color: "#e9e9ff"},
                        {desc: "Venetian -- Galician", source: 90, target: 25, weight: 0.49472582279557, color: "#c8c8ff"},
                        {desc: "Venetian -- Dalmatian", source: 90, target: 16, weight: 0.393480655597282, color: "#e0e0ff"},
                        {desc: "Venetian -- Occitan", source: 90, target: 64, weight: 0.473590592711659, color: "#cdcdff"},
                        {desc: "Venetian -- Italian", source: 90, target: 39, weight: 0.357806351891668, color: "#e9e9ff"},
                        {desc: "Venetian -- Spanish", source: 90, target: 82, weight: 0.378975514530052, color: "#e4e4ff"},
                        {desc: "Aromanian -- Interlingua", source: 4, target: 37, weight: 0.344489281531423, color: "#ececff"},
                        {desc: "Aromanian -- Latin", source: 4, target: 44, weight: 0.455512734764188, color: "#d1d1ff"},
                        {desc: "Aromanian -- French", source: 4, target: 24, weight: 0.372909733105896, color: "#e5e5ff"},
                        {desc: "Aromanian -- Sicilian", source: 4, target: 79, weight: 0.364727152884006, color: "#e7e7ff"},
                        {desc: "Aromanian -- Romansch", source: 4, target: 74, weight: 0.376140466420957, color: "#e4e4ff"},
                        {desc: "Aromanian -- Portuguese", source: 4, target: 72, weight: 0.366667505331733, color: "#e7e7ff"},
                        {desc: "Aromanian -- Catalan", source: 4, target: 10, weight: 0.384397489821863, color: "#e2e2ff"},
                        {desc: "Aromanian -- Asturian", source: 4, target: 5, weight: 0.343688439871062, color: "#ececff"},
                        {desc: "Aromanian -- Occitan", source: 4, target: 64, weight: 0.367301318227061, color: "#e7e7ff"},
                        {desc: "Aromanian -- Dalmatian", source: 4, target: 16, weight: 0.367456446800913, color: "#e7e7ff"},
                        {desc: "Aromanian -- Galician", source: 4, target: 25, weight: 0.35518115774024, color: "#e9e9ff"},
                        {desc: "Aromanian -- Venetian", source: 4, target: 90, weight: 0.347675863942952, color: "#ebebff"},
                        {desc: "Aromanian -- Romanian", source: 4, target: 73, weight: 0.688948606381883, color: "#9999ff"},
                        {desc: "Aromanian -- Italian", source: 4, target: 39, weight: 0.469344559150068, color: "#ceceff"},
                        {desc: "Aromanian -- Spanish", source: 4, target: 82, weight: 0.401114218269015, color: "#dedeff"},
                        {desc: "Malay -- Indonesian", source: 52, target: 36, weight: 0.793783418634996, color: "#8080ff"},
                        {desc: "English -- Middle French", source: 19, target: 57, weight: 0.342103372309722, color: "#ededff"},
                        {desc: "English -- Middle English", source: 19, target: 56, weight: 0.41272955003796, color: "#dcdcff"},
                        {desc: "English -- French", source: 19, target: 24, weight: 0.459346389136046, color: "#d0d0ff"},
                        {desc: "English -- Ido", source: 19, target: 35, weight: 0.426156910108239, color: "#d8d8ff"},
                        {desc: "English -- Afrikaans", source: 19, target: 1, weight: 0.339350481069725, color: "#ededff"},
                        {desc: "English -- Norwegian Nynorsk", source: 19, target: 63, weight: 0.33223600577617, color: "#efefff"},
                        {desc: "English -- Portuguese", source: 19, target: 72, weight: 0.380420027099044, color: "#e3e3ff"},
                        {desc: "English -- Catalan", source: 19, target: 10, weight: 0.506720563919485, color: "#c5c5ff"},
                        {desc: "English -- Asturian", source: 19, target: 5, weight: 0.413194127926012, color: "#dcdcff"},
                        {desc: "English -- Danish", source: 19, target: 17, weight: 0.388107631984554, color: "#e2e2ff"},
                        {desc: "English -- Esperanto", source: 19, target: 20, weight: 0.391954791635541, color: "#e1e1ff"},
                        {desc: "English -- Interlingua", source: 19, target: 37, weight: 0.366515639399694, color: "#e7e7ff"},
                        {desc: "English -- Dutch", source: 19, target: 18, weight: 0.348471023682566, color: "#ebebff"},
                        {desc: "English -- Anglo Norman", source: 19, target: 3, weight: 0.352379354955144, color: "#eaeaff"},
                        {desc: "English -- German", source: 19, target: 26, weight: 0.383575967455355, color: "#e3e3ff"},
                        {desc: "English -- Galician", source: 19, target: 25, weight: 0.437948925008237, color: "#d6d6ff"},
                        {desc: "English -- Italian", source: 19, target: 39, weight: 0.467728249572366, color: "#ceceff"},
                        {desc: "English -- Spanish", source: 19, target: 82, weight: 0.431708810063448, color: "#d7d7ff"},
                        {desc: "Ottoman Turkish -- Turkish", source: 70, target: 88, weight: 0.41935518389369, color: "#dadaff"},
                        {desc: "Ottoman Turkish -- Azeri", source: 70, target: 6, weight: 0.448510953736684, color: "#d3d3ff"},
                        {desc: "Vilamovian -- Old High German", source: 91, target: 66, weight: 0.337488687717853, color: "#eeeeff"},
                        {desc: "Vilamovian -- Luxembourgish", source: 91, target: 49, weight: 0.356720651081035, color: "#e9e9ff"},
                        {desc: "Vilamovian -- Yiddish", source: 91, target: 95, weight: 0.341172054017845, color: "#ededff"},
                        {desc: "Old Norse -- Norwegian BokmÃ¥l", source: 68, target: 62, weight: 0.385730800174531, color: "#e2e2ff"},
                        {desc: "Old Norse -- Norwegian", source: 68, target: 61, weight: 0.383398493547593, color: "#e3e3ff"},
                        {desc: "Old Norse -- Icelandic", source: 68, target: 34, weight: 0.498635098449557, color: "#c7c7ff"},
                        {desc: "Old Norse -- Norwegian Nynorsk", source: 68, target: 63, weight: 0.494928123101656, color: "#c8c8ff"},
                        {desc: "Old Norse -- Old High German", source: 68, target: 66, weight: 0.365735771509022, color: "#e7e7ff"},
                        {desc: "Old Norse -- Old Saxon", source: 68, target: 69, weight: 0.412067327191753, color: "#dcdcff"},
                        {desc: "Old Norse -- Faroese", source: 68, target: 22, weight: 0.506367289299015, color: "#c5c5ff"},
                        {desc: "Old Norse -- Swedish", source: 68, target: 84, weight: 0.522602303378615, color: "#c1c1ff"},
                        {desc: "German -- French", source: 26, target: 24, weight: 0.364921286007301, color: "#e7e7ff"},
                        {desc: "German -- Norwegian", source: 26, target: 61, weight: 0.343411367011175, color: "#ececff"},
                        {desc: "German -- Afrikaans", source: 26, target: 1, weight: 0.458727682674333, color: "#d1d1ff"},
                        {desc: "German -- Old High German", source: 26, target: 66, weight: 0.362626327765679, color: "#e8e8ff"},
                        {desc: "German -- Catalan", source: 26, target: 10, weight: 0.37637926443338, color: "#e4e4ff"},
                        {desc: "German -- Asturian", source: 26, target: 5, weight: 0.363557922848567, color: "#e7e7ff"},
                        {desc: "German -- Swedish", source: 26, target: 84, weight: 0.413474074320589, color: "#dbdbff"},
                        {desc: "German -- Danish", source: 26, target: 17, weight: 0.482270272672015, color: "#cbcbff"},
                        {desc: "German -- Esperanto", source: 26, target: 20, weight: 0.379194493798314, color: "#e4e4ff"},
                        {desc: "German -- English", source: 26, target: 19, weight: 0.383575967455355, color: "#e3e3ff"},
                        {desc: "German -- Dutch", source: 26, target: 18, weight: 0.48627683008492, color: "#cacaff"},
                        {desc: "German -- Luxembourgish", source: 26, target: 49, weight: 0.461843447657729, color: "#d0d0ff"},
                        {desc: "German -- Estonian", source: 26, target: 21, weight: 0.337375379958243, color: "#eeeeff"},
                        {desc: "German -- Galician", source: 26, target: 25, weight: 0.370212470511569, color: "#e6e6ff"},
                        {desc: "German -- Italian", source: 26, target: 39, weight: 0.375213213044565, color: "#e5e5ff"},
                        {desc: "German -- Spanish", source: 26, target: 82, weight: 0.344945175102009, color: "#ececff"},
                        {desc: "Romanian -- Middle French", source: 73, target: 57, weight: 0.352874670927322, color: "#eaeaff"},
                        {desc: "Romanian -- Ido", source: 73, target: 35, weight: 0.385355163911346, color: "#e2e2ff"},
                        {desc: "Romanian -- Portuguese", source: 73, target: 72, weight: 0.421229254562018, color: "#dadaff"},
                        {desc: "Romanian -- Catalan", source: 73, target: 10, weight: 0.457184489739639, color: "#d1d1ff"},
                        {desc: "Romanian -- Asturian", source: 73, target: 5, weight: 0.4643798221846, color: "#cfcfff"},
                        {desc: "Romanian -- Serbo Croatian", source: 73, target: 78, weight: 0.374558547310933, color: "#e5e5ff"},
                        {desc: "Romanian -- Esperanto", source: 73, target: 20, weight: 0.364546568089175, color: "#e7e7ff"},
                        {desc: "Romanian -- Aromanian", source: 73, target: 4, weight: 0.688948606381883, color: "#9999ff"},
                        {desc: "Romanian -- Interlingua", source: 73, target: 37, weight: 0.497723187127196, color: "#c7c7ff"},
                        {desc: "Romanian -- Macedonian", source: 73, target: 50, weight: 0.349779480935974, color: "#ebebff"},
                        {desc: "Romanian -- Anglo Norman", source: 73, target: 3, weight: 0.358139671016734, color: "#e9e9ff"},
                        {desc: "Romanian -- Neapolitan", source: 73, target: 59, weight: 0.392412557179883, color: "#e1e1ff"},
                        {desc: "Romanian -- Old French", source: 73, target: 65, weight: 0.350902799729239, color: "#eaeaff"},
                        {desc: "Romanian -- Galician", source: 73, target: 25, weight: 0.471560042394903, color: "#ceceff"},
                        {desc: "Romanian -- Dalmatian", source: 73, target: 16, weight: 0.407909229344745, color: "#ddddff"},
                        {desc: "Romanian -- Occitan", source: 73, target: 64, weight: 0.413145254359986, color: "#dcdcff"},
                        {desc: "Romanian -- Italian", source: 73, target: 39, weight: 0.336421622686332, color: "#eeeeff"},
                        {desc: "Romanian -- Maltese", source: 73, target: 53, weight: 0.331709479008898, color: "#efefff"},
                        {desc: "Romanian -- Spanish", source: 73, target: 82, weight: 0.46837472375013, color: "#ceceff"},
                        {desc: "Maltese -- Portuguese", source: 53, target: 72, weight: 0.352700522318052, color: "#eaeaff"},
                        {desc: "Maltese -- Serbo Croatian", source: 53, target: 78, weight: 0.34817771974969, color: "#ebebff"},
                        {desc: "Maltese -- Romanian", source: 53, target: 73, weight: 0.331709479008898, color: "#efefff"},
                        {desc: "Maltese -- Spanish", source: 53, target: 82, weight: 0.414205869554214, color: "#dbdbff"},
                        {desc: "Slovene -- Ukrainian", source: 81, target: 89, weight: 0.445007724223072, color: "#d4d4ff"},
                        {desc: "Slovene -- Kashubian", source: 81, target: 41, weight: 0.428365554348115, color: "#d8d8ff"},
                        {desc: "Slovene -- Russian", source: 81, target: 75, weight: 0.373505479436256, color: "#e5e5ff"},
                        {desc: "Slovene -- Macedonian", source: 81, target: 50, weight: 0.621950819837947, color: "#a9a9ff"},
                        {desc: "Slovene -- Slovak", source: 81, target: 80, weight: 0.611144275237352, color: "#acacff"},
                        {desc: "Slovene -- Bulgarian", source: 81, target: 9, weight: 0.449889728479962, color: "#d3d3ff"},
                        {desc: "Slovene -- Polish", source: 81, target: 71, weight: 0.406376251648901, color: "#ddddff"},
                        {desc: "Slovene -- Lithuanian", source: 81, target: 47, weight: 0.367128171360748, color: "#e7e7ff"},
                        {desc: "Slovene -- Serbo Croatian", source: 81, target: 78, weight: 0.477444472214993, color: "#ccccff"},
                        {desc: "Slovene -- Czech", source: 81, target: 15, weight: 0.428716761183654, color: "#d8d8ff"},
                        {desc: "Irish -- Old Irish", source: 38, target: 67, weight: 0.438824264245613, color: "#d5d5ff"},
                        {desc: "Irish -- Scottish Gaelic", source: 38, target: 77, weight: 0.535597927234183, color: "#bebeff"},
                        {desc: "Irish -- Italian", source: 38, target: 39, weight: 0.338669487979475, color: "#ededff"},
                        {desc: "Welsh -- Cornish", source: 93, target: 13, weight: 0.482633817512377, color: "#cbcbff"},
                        {desc: "Welsh -- Breton", source: 93, target: 8, weight: 0.422617869228125, color: "#d9d9ff"},
                        {desc: "Polish -- Ukrainian", source: 71, target: 89, weight: 0.39374949729649, color: "#e0e0ff"},
                        {desc: "Polish -- Kashubian", source: 71, target: 41, weight: 0.55856060390254, color: "#b9b9ff"},
                        {desc: "Polish -- Russian", source: 71, target: 75, weight: 0.474864590731441, color: "#cdcdff"},
                        {desc: "Polish -- Macedonian", source: 71, target: 50, weight: 0.46702798878434, color: "#cfcfff"},
                        {desc: "Polish -- Slovak", source: 71, target: 80, weight: 0.419841873628481, color: "#dadaff"},
                        {desc: "Polish -- Bulgarian", source: 71, target: 9, weight: 0.403647335372303, color: "#dedeff"},
                        {desc: "Polish -- Serbo Croatian", source: 71, target: 78, weight: 0.494244371105413, color: "#c8c8ff"},
                        {desc: "Polish -- Spanish", source: 71, target: 82, weight: 0.360131956296015, color: "#e8e8ff"},
                        {desc: "Polish -- Czech", source: 71, target: 15, weight: 0.457108623980082, color: "#d1d1ff"},
                        {desc: "Polish -- Slovene", source: 71, target: 81, weight: 0.406376251648901, color: "#ddddff"},
                        {desc: "Turkish -- Ottoman Turkish", source: 88, target: 70, weight: 0.41935518389369, color: "#dadaff"},
                        {desc: "Turkish -- Crimean Tatar", source: 88, target: 14, weight: 0.519702094577999, color: "#c2c2ff"},
                        {desc: "Turkish -- Azeri", source: 88, target: 6, weight: 0.610738264809688, color: "#acacff"},
                        {desc: "Hungarian -- Serbo Croatian", source: 33, target: 78, weight: 0.330718801963209, color: "#efefff"},
                        {desc: "Scots -- Middle English", source: 76, target: 56, weight: 0.346856789640438, color: "#ebebff"},
                        {desc: "Scots -- Swedish", source: 76, target: 84, weight: 0.337073478600956, color: "#eeeeff"},
                        {desc: "Basque -- Italian", source: 7, target: 39, weight: 0.340110037494176, color: "#ededff"},
                        {desc: "Basque -- Spanish", source: 7, target: 82, weight: 0.345409803771595, color: "#ececff"},
                        {desc: "Czech -- Ukrainian", source: 15, target: 89, weight: 0.347749355732521, color: "#ebebff"},
                        {desc: "Czech -- Russian", source: 15, target: 75, weight: 0.444272826835361, color: "#d4d4ff"},
                        {desc: "Czech -- Macedonian", source: 15, target: 50, weight: 0.366160622043331, color: "#e7e7ff"},
                        {desc: "Czech -- Slovak", source: 15, target: 80, weight: 0.577090460807085, color: "#b4b4ff"},
                        {desc: "Czech -- Bulgarian", source: 15, target: 9, weight: 0.417485297049287, color: "#dbdbff"},
                        {desc: "Czech -- Polish", source: 15, target: 71, weight: 0.457108623980082, color: "#d1d1ff"},
                        {desc: "Czech -- Serbo Croatian", source: 15, target: 78, weight: 0.507454327485125, color: "#c5c5ff"},
                        {desc: "Czech -- Italian", source: 15, target: 39, weight: 0.360566049747552, color: "#e8e8ff"},
                        {desc: "Czech -- Slovene", source: 15, target: 81, weight: 0.428716761183654, color: "#d8d8ff"},
                        {desc: "Albanian -- French", source: 2, target: 24, weight: 0.332638436769228, color: "#efefff"},
                        {desc: "Albanian -- Serbo Croatian", source: 2, target: 78, weight: 0.341962523633342, color: "#ededff"},
                        {desc: "Albanian -- Italian", source: 2, target: 39, weight: 0.348775832826394, color: "#ebebff"},
                        {desc: "Macedonian -- Ukrainian", source: 50, target: 89, weight: 0.555242950084225, color: "#b9b9ff"},
                        {desc: "Macedonian -- Kashubian", source: 50, target: 41, weight: 0.446421843308669, color: "#d4d4ff"},
                        {desc: "Macedonian -- Russian", source: 50, target: 75, weight: 0.617034094500472, color: "#ababff"},
                        {desc: "Macedonian -- Slovak", source: 50, target: 80, weight: 0.537821776074607, color: "#bebeff"},
                        {desc: "Macedonian -- Bulgarian", source: 50, target: 9, weight: 0.553388526684431, color: "#babaff"},
                        {desc: "Macedonian -- Polish", source: 50, target: 71, weight: 0.46702798878434, color: "#cfcfff"},
                        {desc: "Macedonian -- Serbo Croatian", source: 50, target: 78, weight: 0.826699775239847, color: "#7878ff"},
                        {desc: "Macedonian -- Romanian", source: 50, target: 73, weight: 0.349779480935974, color: "#ebebff"},
                        {desc: "Macedonian -- Spanish", source: 50, target: 82, weight: 0.338151768819287, color: "#eeeeff"},
                        {desc: "Macedonian -- Slovene", source: 50, target: 81, weight: 0.621950819837947, color: "#a9a9ff"},
                        {desc: "Macedonian -- Czech", source: 50, target: 15, weight: 0.366160622043331, color: "#e7e7ff"},
                        {desc: "Bulgarian -- Ukrainian", source: 9, target: 89, weight: 0.417972504573675, color: "#dadaff"},
                        {desc: "Bulgarian -- Russian", source: 9, target: 75, weight: 0.588391468163065, color: "#b1b1ff"},
                        {desc: "Bulgarian -- Macedonian", source: 9, target: 50, weight: 0.553388526684431, color: "#babaff"},
                        {desc: "Bulgarian -- Slovak", source: 9, target: 80, weight: 0.380854221394188, color: "#e3e3ff"},
                        {desc: "Bulgarian -- Polish", source: 9, target: 71, weight: 0.403647335372303, color: "#dedeff"},
                        {desc: "Bulgarian -- Serbo Croatian", source: 9, target: 78, weight: 0.606630994732949, color: "#adadff"},
                        {desc: "Bulgarian -- Italian", source: 9, target: 39, weight: 0.355949732971566, color: "#e9e9ff"},
                        {desc: "Bulgarian -- Spanish", source: 9, target: 82, weight: 0.336503830775619, color: "#eeeeff"},
                        {desc: "Bulgarian -- Czech", source: 9, target: 15, weight: 0.417485297049287, color: "#dbdbff"},
                        {desc: "Bulgarian -- Slovene", source: 9, target: 81, weight: 0.449889728479962, color: "#d3d3ff"},
                        {desc: "Anglo Norman -- Middle French", source: 3, target: 57, weight: 0.582230987488062, color: "#b3b3ff"},
                        {desc: "Anglo Norman -- Latin", source: 3, target: 44, weight: 0.403439567215117, color: "#dedeff"},
                        {desc: "Anglo Norman -- French", source: 3, target: 24, weight: 0.666730596049357, color: "#9f9fff"},
                        {desc: "Anglo Norman -- Ido", source: 3, target: 35, weight: 0.357167916708305, color: "#e9e9ff"},
                        {desc: "Anglo Norman -- Romansch", source: 3, target: 74, weight: 0.433606055577596, color: "#d7d7ff"},
                        {desc: "Anglo Norman -- Portuguese", source: 3, target: 72, weight: 0.412463682103443, color: "#dcdcff"},
                        {desc: "Anglo Norman -- Catalan", source: 3, target: 10, weight: 0.481280386319032, color: "#cbcbff"},
                        {desc: "Anglo Norman -- Asturian", source: 3, target: 5, weight: 0.363178789848462, color: "#e8e8ff"},
                        {desc: "Anglo Norman -- Venetian", source: 3, target: 90, weight: 0.367190446729628, color: "#e7e7ff"},
                        {desc: "Anglo Norman -- Tarantino", source: 3, target: 86, weight: 0.377076863715079, color: "#e4e4ff"},
                        {desc: "Anglo Norman -- Esperanto", source: 3, target: 20, weight: 0.344167157426262, color: "#ececff"},
                        {desc: "Anglo Norman -- English", source: 3, target: 19, weight: 0.352379354955144, color: "#eaeaff"},
                        {desc: "Anglo Norman -- Interlingua", source: 3, target: 37, weight: 0.450176308446742, color: "#d3d3ff"},
                        {desc: "Anglo Norman -- GuernÃ©siais", source: 3, target: 29, weight: 0.478250763671739, color: "#ccccff"},
                        {desc: "Anglo Norman -- JÃ¨rriais", source: 3, target: 40, weight: 0.472992361741817, color: "#cdcdff"},
                        {desc: "Anglo Norman -- Old French", source: 3, target: 65, weight: 0.785736322007098, color: "#8282ff"},
                        {desc: "Anglo Norman -- Galician", source: 3, target: 25, weight: 0.392213812748866, color: "#e1e1ff"},
                        {desc: "Anglo Norman -- Occitan", source: 3, target: 64, weight: 0.451951354306526, color: "#d2d2ff"},
                        {desc: "Anglo Norman -- Romanian", source: 3, target: 73, weight: 0.358139671016734, color: "#e9e9ff"},
                        {desc: "Anglo Norman -- Italian", source: 3, target: 39, weight: 0.502155595810561, color: "#c6c6ff"},
                        {desc: "Anglo Norman -- Spanish", source: 3, target: 82, weight: 0.479974990536552, color: "#ccccff"},
                        {desc: "Latvian -- Russian", source: 45, target: 75, weight: 0.365463934201917, color: "#e7e7ff"},
                        {desc: "Latvian -- Lithuanian", source: 45, target: 47, weight: 0.466696251144178, color: "#cfcfff"},
                        {desc: "Latvian -- Serbo Croatian", source: 45, target: 78, weight: 0.386974745258472, color: "#e2e2ff"},
                        {desc: "Dalmatian -- Middle French", source: 16, target: 57, weight: 0.337841261576902, color: "#eeeeff"},
                        {desc: "Dalmatian -- Latin", source: 16, target: 44, weight: 0.402509867212047, color: "#dedeff"},
                        {desc: "Dalmatian -- French", source: 16, target: 24, weight: 0.412414070610273, color: "#dcdcff"},
                        {desc: "Dalmatian -- Romansch", source: 16, target: 74, weight: 0.430116649340039, color: "#d7d7ff"},
                        {desc: "Dalmatian -- Portuguese", source: 16, target: 72, weight: 0.380336520931508, color: "#e3e3ff"},
                        {desc: "Dalmatian -- Catalan", source: 16, target: 10, weight: 0.335667811014953, color: "#eeeeff"},
                        {desc: "Dalmatian -- Asturian", source: 16, target: 5, weight: 0.346819924749947, color: "#ebebff"},
                        {desc: "Dalmatian -- Venetian", source: 16, target: 90, weight: 0.393480655597282, color: "#e0e0ff"},
                        {desc: "Dalmatian -- Aromanian", source: 16, target: 4, weight: 0.367456446800913, color: "#e7e7ff"},
                        {desc: "Dalmatian -- Interlingua", source: 16, target: 37, weight: 0.383386967620071, color: "#e3e3ff"},
                        {desc: "Dalmatian -- Sicilian", source: 16, target: 79, weight: 0.335206082549648, color: "#eeeeff"},
                        {desc: "Dalmatian -- GuernÃ©siais", source: 16, target: 29, weight: 0.336992300692059, color: "#eeeeff"},
                        {desc: "Dalmatian -- Old French", source: 16, target: 65, weight: 0.379587567884829, color: "#e4e4ff"},
                        {desc: "Dalmatian -- Galician", source: 16, target: 25, weight: 0.377835110963687, color: "#e4e4ff"},
                        {desc: "Dalmatian -- Occitan", source: 16, target: 64, weight: 0.43101599779996, color: "#d7d7ff"},
                        {desc: "Dalmatian -- Italian", source: 16, target: 39, weight: 0.488840594154889, color: "#c9c9ff"},
                        {desc: "Dalmatian -- Romanian", source: 16, target: 73, weight: 0.407909229344745, color: "#ddddff"},
                        {desc: "Dalmatian -- Spanish", source: 16, target: 82, weight: 0.446543507913681, color: "#d4d4ff"},
                        {desc: "Faroese -- Icelandic", source: 22, target: 34, weight: 0.634726612865662, color: "#a6a6ff"},
                        {desc: "Faroese -- Old Norse", source: 22, target: 68, weight: 0.506367289299015, color: "#c5c5ff"},
                        {desc: "Faroese -- Norwegian Nynorsk", source: 22, target: 63, weight: 0.398179173137484, color: "#dfdfff"},
                        {desc: "Faroese -- Old Saxon", source: 22, target: 69, weight: 0.33936728902463, color: "#ededff"},
                        {desc: "Faroese -- Norwegian", source: 22, target: 61, weight: 0.398811634789165, color: "#dfdfff"},
                        {desc: "Faroese -- Swedish", source: 22, target: 84, weight: 0.491961605474191, color: "#c9c9ff"},
                        {desc: "Faroese -- Danish", source: 22, target: 17, weight: 0.378959884723644, color: "#e4e4ff"},
                        {desc: "Yiddish -- Vilamovian", source: 95, target: 91, weight: 0.341172054017845, color: "#ededff"},
                        {desc: "Russian -- Ido", source: 75, target: 35, weight: 0.339401317087572, color: "#ededff"},
                        {desc: "Russian -- Polish", source: 75, target: 71, weight: 0.474864590731441, color: "#cdcdff"},
                        {desc: "Russian -- Lithuanian", source: 75, target: 47, weight: 0.391329256845463, color: "#e1e1ff"},
                        {desc: "Russian -- Portuguese", source: 75, target: 72, weight: 0.331781697302915, color: "#efefff"},
                        {desc: "Russian -- Catalan", source: 75, target: 10, weight: 0.337198976773894, color: "#eeeeff"},
                        {desc: "Russian -- Asturian", source: 75, target: 5, weight: 0.333906512027877, color: "#efefff"},
                        {desc: "Russian -- Crimean Tatar", source: 75, target: 14, weight: 0.348812985856264, color: "#ebebff"},
                        {desc: "Russian -- Serbo Croatian", source: 75, target: 78, weight: 0.545544745837861, color: "#bcbcff"},
                        {desc: "Russian -- Czech", source: 75, target: 15, weight: 0.444272826835361, color: "#d4d4ff"},
                        {desc: "Russian -- Esperanto", source: 75, target: 20, weight: 0.360529833459099, color: "#e8e8ff"},
                        {desc: "Russian -- Ukrainian", source: 75, target: 89, weight: 0.388017011283535, color: "#e2e2ff"},
                        {desc: "Russian -- Kashubian", source: 75, target: 41, weight: 0.410281358450439, color: "#dcdcff"},
                        {desc: "Russian -- Macedonian", source: 75, target: 50, weight: 0.617034094500472, color: "#ababff"},
                        {desc: "Russian -- Slovak", source: 75, target: 80, weight: 0.340300331229255, color: "#ededff"},
                        {desc: "Russian -- Bulgarian", source: 75, target: 9, weight: 0.588391468163065, color: "#b1b1ff"},
                        {desc: "Russian -- Estonian", source: 75, target: 21, weight: 0.333118908554467, color: "#efefff"},
                        {desc: "Russian -- Latvian", source: 75, target: 45, weight: 0.365463934201917, color: "#e7e7ff"},
                        {desc: "Russian -- Galician", source: 75, target: 25, weight: 0.347377695915928, color: "#ebebff"},
                        {desc: "Russian -- Spanish", source: 75, target: 82, weight: 0.338600924187127, color: "#ededff"},
                        {desc: "Russian -- Slovene", source: 75, target: 81, weight: 0.373505479436256, color: "#e5e5ff"},
                        {desc: "Latin -- Ladino", source: 44, target: 43, weight: 0.33315187070235, color: "#efefff"},
                        {desc: "Latin -- French", source: 44, target: 24, weight: 0.388929238314212, color: "#e1e1ff"},
                        {desc: "Latin -- Ido", source: 44, target: 35, weight: 0.444447107454564, color: "#d4d4ff"},
                        {desc: "Latin -- Portuguese", source: 44, target: 72, weight: 0.337784874272019, color: "#eeeeff"},
                        {desc: "Latin -- Catalan", source: 44, target: 10, weight: 0.498454494841333, color: "#c7c7ff"},
                        {desc: "Latin -- Asturian", source: 44, target: 5, weight: 0.491378398719692, color: "#c9c9ff"},
                        {desc: "Latin -- Esperanto", source: 44, target: 20, weight: 0.41856299822701, color: "#dadaff"},
                        {desc: "Latin -- Aromanian", source: 44, target: 4, weight: 0.455512734764188, color: "#d1d1ff"},
                        {desc: "Latin -- Interlingua", source: 44, target: 37, weight: 0.589033911625544, color: "#b1b1ff"},
                        {desc: "Latin -- Anglo Norman", source: 44, target: 3, weight: 0.403439567215117, color: "#dedeff"},
                        {desc: "Latin -- GuernÃ©siais", source: 44, target: 29, weight: 0.340698581242027, color: "#ededff"},
                        {desc: "Latin -- JÃ¨rriais", source: 44, target: 40, weight: 0.338189859158206, color: "#eeeeff"},
                        {desc: "Latin -- Galician", source: 44, target: 25, weight: 0.498349774686784, color: "#c7c7ff"},
                        {desc: "Latin -- Dalmatian", source: 44, target: 16, weight: 0.402509867212047, color: "#dedeff"},
                        {desc: "Latin -- Italian", source: 44, target: 39, weight: 0.412094795720835, color: "#dcdcff"},
                        {desc: "Latin -- Spanish", source: 44, target: 82, weight: 0.428772020735921, color: "#d8d8ff"},
                        {desc: "Norwegian -- Limburgish", source: 61, target: 46, weight: 0.458480028958803, color: "#d1d1ff"},
                        {desc: "Norwegian -- Afrikaans", source: 61, target: 1, weight: 0.379073157586077, color: "#e4e4ff"},
                        {desc: "Norwegian -- Icelandic", source: 61, target: 34, weight: 0.339681666988236, color: "#ededff"},
                        {desc: "Norwegian -- Old Norse", source: 61, target: 68, weight: 0.383398493547593, color: "#e3e3ff"},
                        {desc: "Norwegian -- German", source: 61, target: 26, weight: 0.343411367011175, color: "#ececff"},
                        {desc: "Norwegian -- Old Saxon", source: 61, target: 69, weight: 0.332354890448706, color: "#efefff"},
                        {desc: "Norwegian -- Swedish", source: 61, target: 84, weight: 0.593946020943778, color: "#b0b0ff"},
                        {desc: "Norwegian -- Faroese", source: 61, target: 22, weight: 0.398811634789165, color: "#dfdfff"},
                        {desc: "Norwegian -- Danish", source: 61, target: 17, weight: 0.591770099629418, color: "#b1b1ff"},
                        {desc: "Cornish -- Welsh", source: 13, target: 93, weight: 0.482633817512377, color: "#cbcbff"},
                        {desc: "Cornish -- Breton", source: 13, target: 8, weight: 0.549637563179194, color: "#bbbbff"},
                        {desc: "Norwegian Nynorsk -- English", source: 63, target: 19, weight: 0.33223600577617, color: "#efefff"},
                        {desc: "Norwegian Nynorsk -- Norwegian BokmÃ¥l", source: 63, target: 62, weight: 0.827502482390885, color: "#7878ff"},
                        {desc: "Norwegian Nynorsk -- Old Norse", source: 63, target: 68, weight: 0.494928123101656, color: "#c8c8ff"},
                        {desc: "Norwegian Nynorsk -- Old Saxon", source: 63, target: 69, weight: 0.345267587349194, color: "#ececff"},
                        {desc: "Norwegian Nynorsk -- Swedish", source: 63, target: 84, weight: 0.638151629540905, color: "#a6a6ff"},
                        {desc: "Norwegian Nynorsk -- Faroese", source: 63, target: 22, weight: 0.398179173137484, color: "#dfdfff"},
                        {desc: "Norwegian Nynorsk -- Serbo Croatian", source: 63, target: 78, weight: 0.359705830258983, color: "#e8e8ff"},
                        {desc: "Norwegian Nynorsk -- Danish", source: 63, target: 17, weight: 0.397917704979579, color: "#dfdfff"},
                        {desc: "Norwegian Nynorsk -- Spanish", source: 63, target: 82, weight: 0.384715609605425, color: "#e2e2ff"},
                        {desc: "Romansch -- Middle French", source: 74, target: 57, weight: 0.362946535310438, color: "#e8e8ff"},
                        {desc: "Romansch -- Ido", source: 74, target: 35, weight: 0.368762129951607, color: "#e6e6ff"},
                        {desc: "Romansch -- Portuguese", source: 74, target: 72, weight: 0.378479726981239, color: "#e4e4ff"},
                        {desc: "Romansch -- Catalan", source: 74, target: 10, weight: 0.421857982302991, color: "#d9d9ff"},
                        {desc: "Romansch -- Asturian", source: 74, target: 5, weight: 0.413131493985938, color: "#dcdcff"},
                        {desc: "Romansch -- Venetian", source: 74, target: 90, weight: 0.390569955235945, color: "#e1e1ff"},
                        {desc: "Romansch -- Aromanian", source: 74, target: 4, weight: 0.376140466420957, color: "#e4e4ff"},
                        {desc: "Romansch -- Interlingua", source: 74, target: 37, weight: 0.48871156623808, color: "#c9c9ff"},
                        {desc: "Romansch -- Anglo Norman", source: 74, target: 3, weight: 0.433606055577596, color: "#d7d7ff"},
                        {desc: "Romansch -- Sicilian", source: 74, target: 79, weight: 0.352881086138544, color: "#eaeaff"},
                        {desc: "Romansch -- GuernÃ©siais", source: 74, target: 29, weight: 0.399178867049061, color: "#dfdfff"},
                        {desc: "Romansch -- JÃ¨rriais", source: 74, target: 40, weight: 0.376902230565539, color: "#e4e4ff"},
                        {desc: "Romansch -- Neapolitan", source: 74, target: 59, weight: 0.394529865123332, color: "#e0e0ff"},
                        {desc: "Romansch -- Old French", source: 74, target: 65, weight: 0.390792143695495, color: "#e1e1ff"},
                        {desc: "Romansch -- Galician", source: 74, target: 25, weight: 0.443124286458585, color: "#d4d4ff"},
                        {desc: "Romansch -- Dalmatian", source: 74, target: 16, weight: 0.430116649340039, color: "#d7d7ff"},
                        {desc: "Romansch -- Occitan", source: 74, target: 64, weight: 0.480403134977914, color: "#cbcbff"},
                        {desc: "Romansch -- Spanish", source: 74, target: 82, weight: 0.461969004017388, color: "#d0d0ff"},
                        {desc: "Portuguese -- Middle French", source: 72, target: 57, weight: 0.411126216081552, color: "#dcdcff"},
                        {desc: "Portuguese -- Ladino", source: 72, target: 43, weight: 0.468473742779661, color: "#ceceff"},
                        {desc: "Portuguese -- Russian", source: 72, target: 75, weight: 0.331781697302915, color: "#efefff"},
                        {desc: "Portuguese -- Latin", source: 72, target: 44, weight: 0.337784874272019, color: "#eeeeff"},
                        {desc: "Portuguese -- French", source: 72, target: 24, weight: 0.391336320901031, color: "#e1e1ff"},
                        {desc: "Portuguese -- Ido", source: 72, target: 35, weight: 0.503669338965718, color: "#c6c6ff"},
                        {desc: "Portuguese -- Romansch", source: 72, target: 74, weight: 0.378479726981239, color: "#e4e4ff"},
                        {desc: "Portuguese -- Catalan", source: 72, target: 10, weight: 0.626720254277922, color: "#a8a8ff"},
                        {desc: "Portuguese -- Asturian", source: 72, target: 5, weight: 0.6507332756502, color: "#a3a3ff"},
                        {desc: "Portuguese -- Swedish", source: 72, target: 84, weight: 0.33071342983268, color: "#efefff"},
                        {desc: "Portuguese -- Serbo Croatian", source: 72, target: 78, weight: 0.372424262495292, color: "#e5e5ff"},
                        {desc: "Portuguese -- Venetian", source: 72, target: 90, weight: 0.429528632383963, color: "#d8d8ff"},
                        {desc: "Portuguese -- Tarantino", source: 72, target: 86, weight: 0.330283281927425, color: "#efefff"},
                        {desc: "Portuguese -- Esperanto", source: 72, target: 20, weight: 0.437925126083562, color: "#d6d6ff"},
                        {desc: "Portuguese -- Aromanian", source: 72, target: 4, weight: 0.366667505331733, color: "#e7e7ff"},
                        {desc: "Portuguese -- English", source: 72, target: 19, weight: 0.380420027099044, color: "#e3e3ff"},
                        {desc: "Portuguese -- Interlingua", source: 72, target: 37, weight: 0.626090722011797, color: "#a8a8ff"},
                        {desc: "Portuguese -- Anglo Norman", source: 72, target: 3, weight: 0.412463682103443, color: "#dcdcff"},
                        {desc: "Portuguese -- GuernÃ©siais", source: 72, target: 29, weight: 0.375635686562814, color: "#e5e5ff"},
                        {desc: "Portuguese -- Haitian Creole", source: 72, target: 30, weight: 0.335762482287106, color: "#eeeeff"},
                        {desc: "Portuguese -- JÃ¨rriais", source: 72, target: 40, weight: 0.351746695683239, color: "#eaeaff"},
                        {desc: "Portuguese -- Old French", source: 72, target: 65, weight: 0.400068816896919, color: "#dfdfff"},
                        {desc: "Portuguese -- Neapolitan", source: 72, target: 59, weight: 0.381416287949376, color: "#e3e3ff"},
                        {desc: "Portuguese -- Occitan", source: 72, target: 64, weight: 0.545520112847621, color: "#bcbcff"},
                        {desc: "Portuguese -- Dalmatian", source: 72, target: 16, weight: 0.380336520931508, color: "#e3e3ff"},
                        {desc: "Portuguese -- Galician", source: 72, target: 25, weight: 0.776603190062558, color: "#8484ff"},
                        {desc: "Portuguese -- Romanian", source: 72, target: 73, weight: 0.421229254562018, color: "#dadaff"},
                        {desc: "Portuguese -- Maltese", source: 72, target: 53, weight: 0.352700522318052, color: "#eaeaff"},
                        {desc: "Portuguese -- Italian", source: 72, target: 39, weight: 0.413870591371265, color: "#dbdbff"},
                        {desc: "Portuguese -- Spanish", source: 72, target: 82, weight: 0.752259502478234, color: "#8a8aff"},
                        {desc: "Manx -- Scottish Gaelic", source: 54, target: 77, weight: 0.36063641209494, color: "#e8e8ff"},
                        {desc: "Swedish -- Middle English", source: 84, target: 56, weight: 0.349682158597595, color: "#ebebff"},
                        {desc: "Swedish -- Norwegian", source: 84, target: 61, weight: 0.593946020943778, color: "#b0b0ff"},
                        {desc: "Swedish -- Ido", source: 84, target: 35, weight: 0.357655373188644, color: "#e9e9ff"},
                        {desc: "Swedish -- Icelandic", source: 84, target: 34, weight: 0.422333850446346, color: "#d9d9ff"},
                        {desc: "Swedish -- Afrikaans", source: 84, target: 1, weight: 0.41799460855727, color: "#dadaff"},
                        {desc: "Swedish -- Norwegian Nynorsk", source: 84, target: 63, weight: 0.638151629540905, color: "#a6a6ff"},
                        {desc: "Swedish -- Old Saxon", source: 84, target: 69, weight: 0.432320257475678, color: "#d7d7ff"},
                        {desc: "Swedish -- Old High German", source: 84, target: 66, weight: 0.392732168362588, color: "#e0e0ff"},
                        {desc: "Swedish -- Portuguese", source: 84, target: 72, weight: 0.33071342983268, color: "#efefff"},
                        {desc: "Swedish -- Catalan", source: 84, target: 10, weight: 0.357403684885824, color: "#e9e9ff"},
                        {desc: "Swedish -- Asturian", source: 84, target: 5, weight: 0.338321165237517, color: "#eeeeff"},
                        {desc: "Swedish -- Scots", source: 84, target: 76, weight: 0.337073478600956, color: "#eeeeff"},
                        {desc: "Swedish -- Danish", source: 84, target: 17, weight: 0.619701114644877, color: "#aaaaff"},
                        {desc: "Swedish -- Esperanto", source: 84, target: 20, weight: 0.350373200176643, color: "#ebebff"},
                        {desc: "Swedish -- Interlingua", source: 84, target: 37, weight: 0.346906777974722, color: "#ebebff"},
                        {desc: "Swedish -- Norwegian BokmÃ¥l", source: 84, target: 62, weight: 0.672731836736953, color: "#9d9dff"},
                        {desc: "Swedish -- Dutch", source: 84, target: 18, weight: 0.377685454713245, color: "#e4e4ff"},
                        {desc: "Swedish -- Luxembourgish", source: 84, target: 49, weight: 0.382155662158124, color: "#e3e3ff"},
                        {desc: "Swedish -- Estonian", source: 84, target: 21, weight: 0.334575916210428, color: "#eeeeff"},
                        {desc: "Swedish -- Limburgish", source: 84, target: 46, weight: 0.451317994144019, color: "#d2d2ff"},
                        {desc: "Swedish -- Old Norse", source: 84, target: 68, weight: 0.522602303378615, color: "#c1c1ff"},
                        {desc: "Swedish -- German", source: 84, target: 26, weight: 0.413474074320589, color: "#dbdbff"},
                        {desc: "Swedish -- Galician", source: 84, target: 25, weight: 0.34737255408739, color: "#ebebff"},
                        {desc: "Swedish -- Faroese", source: 84, target: 22, weight: 0.491961605474191, color: "#c9c9ff"},
                        {desc: "Serbo Croatian -- Russian", source: 78, target: 75, weight: 0.545544745837861, color: "#bcbcff"},
                        {desc: "Serbo Croatian -- Ido", source: 78, target: 35, weight: 0.382975071438491, color: "#e3e3ff"},
                        {desc: "Serbo Croatian -- Polish", source: 78, target: 71, weight: 0.494244371105413, color: "#c8c8ff"},
                        {desc: "Serbo Croatian -- Norwegian Nynorsk", source: 78, target: 63, weight: 0.359705830258983, color: "#e8e8ff"},
                        {desc: "Serbo Croatian -- Lithuanian", source: 78, target: 47, weight: 0.433665258850984, color: "#d7d7ff"},
                        {desc: "Serbo Croatian -- Portuguese", source: 78, target: 72, weight: 0.372424262495292, color: "#e5e5ff"},
                        {desc: "Serbo Croatian -- Catalan", source: 78, target: 10, weight: 0.387358223173039, color: "#e2e2ff"},
                        {desc: "Serbo Croatian -- Hungarian", source: 78, target: 33, weight: 0.330718801963209, color: "#efefff"},
                        {desc: "Serbo Croatian -- Asturian", source: 78, target: 5, weight: 0.366509452156307, color: "#e7e7ff"},
                        {desc: "Serbo Croatian -- Crimean Tatar", source: 78, target: 14, weight: 0.350887868639148, color: "#eaeaff"},
                        {desc: "Serbo Croatian -- Czech", source: 78, target: 15, weight: 0.507454327485125, color: "#c5c5ff"},
                        {desc: "Serbo Croatian -- Danish", source: 78, target: 17, weight: 0.331699201358347, color: "#efefff"},
                        {desc: "Serbo Croatian -- Esperanto", source: 78, target: 20, weight: 0.408773211316685, color: "#ddddff"},
                        {desc: "Serbo Croatian -- Kashubian", source: 78, target: 41, weight: 0.464237028174549, color: "#cfcfff"},
                        {desc: "Serbo Croatian -- Interlingua", source: 78, target: 37, weight: 0.331621492454645, color: "#efefff"},
                        {desc: "Serbo Croatian -- Albanian", source: 78, target: 2, weight: 0.341962523633342, color: "#ededff"},
                        {desc: "Serbo Croatian -- Macedonian", source: 78, target: 50, weight: 0.826699775239847, color: "#7878ff"},
                        {desc: "Serbo Croatian -- Slovak", source: 78, target: 80, weight: 0.371530069722657, color: "#e6e6ff"},
                        {desc: "Serbo Croatian -- Bulgarian", source: 78, target: 9, weight: 0.606630994732949, color: "#adadff"},
                        {desc: "Serbo Croatian -- Estonian", source: 78, target: 21, weight: 0.34923950580843, color: "#ebebff"},
                        {desc: "Serbo Croatian -- Latvian", source: 78, target: 45, weight: 0.386974745258472, color: "#e2e2ff"},
                        {desc: "Serbo Croatian -- Galician", source: 78, target: 25, weight: 0.38442273046054, color: "#e2e2ff"},
                        {desc: "Serbo Croatian -- Romanian", source: 78, target: 73, weight: 0.374558547310933, color: "#e5e5ff"},
                        {desc: "Serbo Croatian -- Maltese", source: 78, target: 53, weight: 0.34817771974969, color: "#ebebff"},
                        {desc: "Serbo Croatian -- Spanish", source: 78, target: 82, weight: 0.333012747324426, color: "#efefff"},
                        {desc: "Serbo Croatian -- Slovene", source: 78, target: 81, weight: 0.477444472214993, color: "#ccccff"},
                        {desc: "Danish -- English", source: 17, target: 19, weight: 0.388107631984554, color: "#e2e2ff"},
                        {desc: "Danish -- Norwegian BokmÃ¥l", source: 17, target: 62, weight: 0.460227829338794, color: "#d0d0ff"},
                        {desc: "Danish -- Dutch", source: 17, target: 18, weight: 0.460927817513525, color: "#d0d0ff"},
                        {desc: "Danish -- French", source: 17, target: 24, weight: 0.365774760105095, color: "#e7e7ff"},
                        {desc: "Danish -- Norwegian", source: 17, target: 61, weight: 0.591770099629418, color: "#b1b1ff"},
                        {desc: "Danish -- Afrikaans", source: 17, target: 1, weight: 0.38995150549108, color: "#e1e1ff"},
                        {desc: "Danish -- Icelandic", source: 17, target: 34, weight: 0.384614463754934, color: "#e2e2ff"},
                        {desc: "Danish -- Norwegian Nynorsk", source: 17, target: 63, weight: 0.397917704979579, color: "#dfdfff"},
                        {desc: "Danish -- German", source: 17, target: 26, weight: 0.482270272672015, color: "#cbcbff"},
                        {desc: "Danish -- Swedish", source: 17, target: 84, weight: 0.619701114644877, color: "#aaaaff"},
                        {desc: "Danish -- Serbo Croatian", source: 17, target: 78, weight: 0.331699201358347, color: "#efefff"},
                        {desc: "Danish -- Faroese", source: 17, target: 22, weight: 0.378959884723644, color: "#e4e4ff"},
                        {desc: "Danish -- Italian", source: 17, target: 39, weight: 0.362425141274586, color: "#e8e8ff"},
                        {desc: "Danish -- Spanish", source: 17, target: 82, weight: 0.33340999100283, color: "#efefff"},
                        {desc: "Esperanto -- Russian", source: 20, target: 75, weight: 0.360529833459099, color: "#e8e8ff"},
                        {desc: "Esperanto -- Latin", source: 20, target: 44, weight: 0.41856299822701, color: "#dadaff"},
                        {desc: "Esperanto -- French", source: 20, target: 24, weight: 0.442857168347579, color: "#d4d4ff"},
                        {desc: "Esperanto -- Ido", source: 20, target: 35, weight: 0.660113246441814, color: "#a0a0ff"},
                        {desc: "Esperanto -- Finnish", source: 20, target: 23, weight: 0.34683290446818, color: "#ebebff"},
                        {desc: "Esperanto -- Portuguese", source: 20, target: 72, weight: 0.437925126083562, color: "#d6d6ff"},
                        {desc: "Esperanto -- Catalan", source: 20, target: 10, weight: 0.440445112817385, color: "#d5d5ff"},
                        {desc: "Esperanto -- Asturian", source: 20, target: 5, weight: 0.420966818196268, color: "#dadaff"},
                        {desc: "Esperanto -- Swedish", source: 20, target: 84, weight: 0.350373200176643, color: "#ebebff"},
                        {desc: "Esperanto -- Serbo Croatian", source: 20, target: 78, weight: 0.408773211316685, color: "#ddddff"},
                        {desc: "Esperanto -- Venetian", source: 20, target: 90, weight: 0.332061866781988, color: "#efefff"},
                        {desc: "Esperanto -- English", source: 20, target: 19, weight: 0.391954791635541, color: "#e1e1ff"},
                        {desc: "Esperanto -- Interlingua", source: 20, target: 37, weight: 0.381249446359888, color: "#e3e3ff"},
                        {desc: "Esperanto -- Anglo Norman", source: 20, target: 3, weight: 0.344167157426262, color: "#ececff"},
                        {desc: "Esperanto -- German", source: 20, target: 26, weight: 0.379194493798314, color: "#e4e4ff"},
                        {desc: "Esperanto -- Galician", source: 20, target: 25, weight: 0.428492654284028, color: "#d8d8ff"},
                        {desc: "Esperanto -- Italian", source: 20, target: 39, weight: 0.508025213022236, color: "#c5c5ff"},
                        {desc: "Esperanto -- Romanian", source: 20, target: 73, weight: 0.364546568089175, color: "#e7e7ff"},
                        {desc: "Esperanto -- Spanish", source: 20, target: 82, weight: 0.4864910989853, color: "#cacaff"},
                        {desc: "Ukrainian -- Kashubian", source: 89, target: 41, weight: 0.449688961872688, color: "#d3d3ff"},
                        {desc: "Ukrainian -- Russian", source: 89, target: 75, weight: 0.388017011283535, color: "#e2e2ff"},
                        {desc: "Ukrainian -- Macedonian", source: 89, target: 50, weight: 0.555242950084225, color: "#b9b9ff"},
                        {desc: "Ukrainian -- Slovak", source: 89, target: 80, weight: 0.483545121442469, color: "#cbcbff"},
                        {desc: "Ukrainian -- Bulgarian", source: 89, target: 9, weight: 0.417972504573675, color: "#dadaff"},
                        {desc: "Ukrainian -- Polish", source: 89, target: 71, weight: 0.39374949729649, color: "#e0e0ff"},
                        {desc: "Ukrainian -- Czech", source: 89, target: 15, weight: 0.347749355732521, color: "#ebebff"},
                        {desc: "Ukrainian -- Slovene", source: 89, target: 81, weight: 0.445007724223072, color: "#d4d4ff"},
                        {desc: "Kashubian -- Ukrainian", source: 41, target: 89, weight: 0.449688961872688, color: "#d3d3ff"},
                        {desc: "Kashubian -- Polish", source: 41, target: 71, weight: 0.55856060390254, color: "#b9b9ff"},
                        {desc: "Kashubian -- Russian", source: 41, target: 75, weight: 0.410281358450439, color: "#dcdcff"},
                        {desc: "Kashubian -- Macedonian", source: 41, target: 50, weight: 0.446421843308669, color: "#d4d4ff"},
                        {desc: "Kashubian -- Slovak", source: 41, target: 80, weight: 0.491026906191178, color: "#c9c9ff"},
                        {desc: "Kashubian -- Serbo Croatian", source: 41, target: 78, weight: 0.464237028174549, color: "#cfcfff"},
                        {desc: "Kashubian -- Slovene", source: 41, target: 81, weight: 0.428365554348115, color: "#d8d8ff"},
                        {desc: "Old Irish -- Scottish Gaelic", source: 67, target: 77, weight: 0.572606444729963, color: "#b5b5ff"},
                        {desc: "Old Irish -- Irish", source: 67, target: 38, weight: 0.438824264245613, color: "#d5d5ff"},
                        {desc: "Luxembourgish -- Vilamovian", source: 49, target: 91, weight: 0.356720651081035, color: "#e9e9ff"},
                        {desc: "Luxembourgish -- Afrikaans", source: 49, target: 1, weight: 0.374454045478179, color: "#e5e5ff"},
                        {desc: "Luxembourgish -- Old High German", source: 49, target: 66, weight: 0.349988322574776, color: "#ebebff"},
                        {desc: "Luxembourgish -- German", source: 49, target: 26, weight: 0.461843447657729, color: "#d0d0ff"},
                        {desc: "Luxembourgish -- Dutch", source: 49, target: 18, weight: 0.356367716560044, color: "#e9e9ff"},
                        {desc: "Luxembourgish -- Swedish", source: 49, target: 84, weight: 0.382155662158124, color: "#e3e3ff"},
                        {desc: "Luxembourgish -- Limburgish", source: 49, target: 46, weight: 0.419461839763742, color: "#dadaff"},
                        {desc: "Slovak -- Ukrainian", source: 80, target: 89, weight: 0.483545121442469, color: "#cbcbff"},
                        {desc: "Slovak -- Kashubian", source: 80, target: 41, weight: 0.491026906191178, color: "#c9c9ff"},
                        {desc: "Slovak -- Russian", source: 80, target: 75, weight: 0.340300331229255, color: "#ededff"},
                        {desc: "Slovak -- Macedonian", source: 80, target: 50, weight: 0.537821776074607, color: "#bebeff"},
                        {desc: "Slovak -- Bulgarian", source: 80, target: 9, weight: 0.380854221394188, color: "#e3e3ff"},
                        {desc: "Slovak -- Polish", source: 80, target: 71, weight: 0.419841873628481, color: "#dadaff"},
                        {desc: "Slovak -- Serbo Croatian", source: 80, target: 78, weight: 0.371530069722657, color: "#e6e6ff"},
                        {desc: "Slovak -- Czech", source: 80, target: 15, weight: 0.577090460807085, color: "#b4b4ff"},
                        {desc: "Slovak -- Slovene", source: 80, target: 81, weight: 0.611144275237352, color: "#acacff"},
                        {desc: "Estonian -- Russian", source: 21, target: 75, weight: 0.333118908554467, color: "#efefff"},
                        {desc: "Estonian -- Finnish", source: 21, target: 23, weight: 0.518418718463343, color: "#c2c2ff"},
                        {desc: "Estonian -- German", source: 21, target: 26, weight: 0.337375379958243, color: "#eeeeff"},
                        {desc: "Estonian -- Swedish", source: 21, target: 84, weight: 0.334575916210428, color: "#eeeeff"},
                        {desc: "Estonian -- Serbo Croatian", source: 21, target: 78, weight: 0.34923950580843, color: "#ebebff"},
                        {desc: "Indonesian -- Malay", source: 36, target: 52, weight: 0.793783418634996, color: "#8080ff"},
                        {desc: "Limburgish -- Afrikaans", source: 46, target: 1, weight: 0.397748544651975, color: "#dfdfff"},
                        {desc: "Limburgish -- Middle English", source: 46, target: 56, weight: 0.341223742176847, color: "#ededff"},
                        {desc: "Limburgish -- Luxembourgish", source: 46, target: 49, weight: 0.419461839763742, color: "#dadaff"},
                        {desc: "Limburgish -- Norwegian", source: 46, target: 61, weight: 0.458480028958803, color: "#d1d1ff"},
                        {desc: "Limburgish -- Swedish", source: 46, target: 84, weight: 0.451317994144019, color: "#d2d2ff"},
                        {desc: "Limburgish -- West Frisian", source: 46, target: 94, weight: 0.476602358071982, color: "#ccccff"},
                        {desc: "GuernÃ©siais -- Middle French", source: 29, target: 57, weight: 0.544639134176687, color: "#bcbcff"},
                        {desc: "GuernÃ©siais -- Latin", source: 29, target: 44, weight: 0.340698581242027, color: "#ededff"},
                        {desc: "GuernÃ©siais -- French", source: 29, target: 24, weight: 0.367878310516009, color: "#e6e6ff"},
                        {desc: "GuernÃ©siais -- Ido", source: 29, target: 35, weight: 0.336143608715223, color: "#eeeeff"},
                        {desc: "GuernÃ©siais -- Romansch", source: 29, target: 74, weight: 0.399178867049061, color: "#dfdfff"},
                        {desc: "GuernÃ©siais -- Portuguese", source: 29, target: 72, weight: 0.375635686562814, color: "#e5e5ff"},
                        {desc: "GuernÃ©siais -- Catalan", source: 29, target: 10, weight: 0.355508124325858, color: "#e9e9ff"},
                        {desc: "GuernÃ©siais -- Asturian", source: 29, target: 5, weight: 0.337595941995253, color: "#eeeeff"},
                        {desc: "GuernÃ©siais -- Venetian", source: 29, target: 90, weight: 0.361223548195049, color: "#e8e8ff"},
                        {desc: "GuernÃ©siais -- Tarantino", source: 29, target: 86, weight: 0.365581187032024, color: "#e7e7ff"},
                        {desc: "GuernÃ©siais -- Interlingua", source: 29, target: 37, weight: 0.451383208406383, color: "#d2d2ff"},
                        {desc: "GuernÃ©siais -- Anglo Norman", source: 29, target: 3, weight: 0.478250763671739, color: "#ccccff"},
                        {desc: "GuernÃ©siais -- Haitian Creole", source: 29, target: 30, weight: 0.397408371484732, color: "#dfdfff"},
                        {desc: "GuernÃ©siais -- JÃ¨rriais", source: 29, target: 40, weight: 0.641070776168377, color: "#a5a5ff"},
                        {desc: "GuernÃ©siais -- Old French", source: 29, target: 65, weight: 0.564321121461562, color: "#b7b7ff"},
                        {desc: "GuernÃ©siais -- Galician", source: 29, target: 25, weight: 0.333609303315481, color: "#efefff"},
                        {desc: "GuernÃ©siais -- Dalmatian", source: 29, target: 16, weight: 0.336992300692059, color: "#eeeeff"},
                        {desc: "GuernÃ©siais -- Occitan", source: 29, target: 64, weight: 0.484056164367565, color: "#cbcbff"},
                        {desc: "GuernÃ©siais -- Italian", source: 29, target: 39, weight: 0.433114737008525, color: "#d7d7ff"},
                        {desc: "GuernÃ©siais -- Spanish", source: 29, target: 82, weight: 0.40456459620677, color: "#dedeff"},
                        {desc: "Haitian Creole -- Middle French", source: 30, target: 57, weight: 0.355952757961896, color: "#e9e9ff"},
                        {desc: "Haitian Creole -- GuernÃ©siais", source: 30, target: 29, weight: 0.397408371484732, color: "#dfdfff"},
                        {desc: "Haitian Creole -- JÃ¨rriais", source: 30, target: 40, weight: 0.402471905080681, color: "#dedeff"},
                        {desc: "Haitian Creole -- Portuguese", source: 30, target: 72, weight: 0.335762482287106, color: "#eeeeff"},
                        {desc: "Haitian Creole -- Old French", source: 30, target: 65, weight: 0.369244859925694, color: "#e6e6ff"},
                        {desc: "Haitian Creole -- Occitan", source: 30, target: 64, weight: 0.34766504363199, color: "#ebebff"},
                        {desc: "Haitian Creole -- Italian", source: 30, target: 39, weight: 0.411263448374154, color: "#dcdcff"},
                        {desc: "Haitian Creole -- Spanish", source: 30, target: 82, weight: 0.387285716311876, color: "#e2e2ff"},
                        {desc: "Old French -- Middle French", source: 65, target: 57, weight: 0.684145679444443, color: "#9b9bff"},
                        {desc: "Old French -- Middle English", source: 65, target: 56, weight: 0.356594703099833, color: "#e9e9ff"},
                        {desc: "Old French -- French", source: 65, target: 24, weight: 0.428117130845519, color: "#d8d8ff"},
                        {desc: "Old French -- Ido", source: 65, target: 35, weight: 0.386890941525547, color: "#e2e2ff"},
                        {desc: "Old French -- Romansch", source: 65, target: 74, weight: 0.390792143695495, color: "#e1e1ff"},
                        {desc: "Old French -- Portuguese", source: 65, target: 72, weight: 0.400068816896919, color: "#dfdfff"},
                        {desc: "Old French -- Catalan", source: 65, target: 10, weight: 0.428253822458563, color: "#d8d8ff"},
                        {desc: "Old French -- Asturian", source: 65, target: 5, weight: 0.386245373707668, color: "#e2e2ff"},
                        {desc: "Old French -- Venetian", source: 65, target: 90, weight: 0.357574734792802, color: "#e9e9ff"},
                        {desc: "Old French -- Tarantino", source: 65, target: 86, weight: 0.345521832232947, color: "#ececff"},
                        {desc: "Old French -- Interlingua", source: 65, target: 37, weight: 0.492747635230785, color: "#c8c8ff"},
                        {desc: "Old French -- Anglo Norman", source: 65, target: 3, weight: 0.785736322007098, color: "#8282ff"},
                        {desc: "Old French -- GuernÃ©siais", source: 65, target: 29, weight: 0.564321121461562, color: "#b7b7ff"},
                        {desc: "Old French -- Haitian Creole", source: 65, target: 30, weight: 0.369244859925694, color: "#e6e6ff"},
                        {desc: "Old French -- JÃ¨rriais", source: 65, target: 40, weight: 0.510515583573647, color: "#c4c4ff"},
                        {desc: "Old French -- Galician", source: 65, target: 25, weight: 0.404917401594675, color: "#dedeff"},
                        {desc: "Old French -- Dalmatian", source: 65, target: 16, weight: 0.379587567884829, color: "#e4e4ff"},
                        {desc: "Old French -- Occitan", source: 65, target: 64, weight: 0.501420971902111, color: "#c6c6ff"},
                        {desc: "Old French -- Romanian", source: 65, target: 73, weight: 0.350902799729239, color: "#eaeaff"},
                        {desc: "Old French -- Spanish", source: 65, target: 82, weight: 0.473318055165018, color: "#cdcdff"},
                        {desc: "Breton -- Cornish", source: 8, target: 13, weight: 0.549637563179194, color: "#bbbbff"},
                        {desc: "Breton -- Welsh", source: 8, target: 93, weight: 0.422617869228125, color: "#d9d9ff"},
                        {desc: "West Frisian -- Afrikaans", source: 94, target: 1, weight: 0.456403821706772, color: "#d1d1ff"},
                        {desc: "West Frisian -- Old Saxon", source: 94, target: 69, weight: 0.344988561667322, color: "#ececff"},
                        {desc: "West Frisian -- Limburgish", source: 94, target: 46, weight: 0.476602358071982, color: "#ccccff"},
                        {desc: "Spanish -- Middle French", source: 82, target: 57, weight: 0.486472015453226, color: "#cacaff"},
                        {desc: "Spanish -- Ladino", source: 82, target: 43, weight: 0.57732095007192, color: "#b4b4ff"},
                        {desc: "Spanish -- Russian", source: 82, target: 75, weight: 0.338600924187127, color: "#ededff"},
                        {desc: "Spanish -- Latin", source: 82, target: 44, weight: 0.428772020735921, color: "#d8d8ff"},
                        {desc: "Spanish -- French", source: 82, target: 24, weight: 0.493667332955786, color: "#c8c8ff"},
                        {desc: "Spanish -- Ido", source: 82, target: 35, weight: 0.572000386361266, color: "#b5b5ff"},
                        {desc: "Spanish -- Hiligaynon", source: 82, target: 32, weight: 0.358396669128018, color: "#e9e9ff"},
                        {desc: "Spanish -- Norwegian Nynorsk", source: 82, target: 63, weight: 0.384715609605425, color: "#e2e2ff"},
                        {desc: "Spanish -- Romansch", source: 82, target: 74, weight: 0.461969004017388, color: "#d0d0ff"},
                        {desc: "Spanish -- Portuguese", source: 82, target: 72, weight: 0.752259502478234, color: "#8a8aff"},
                        {desc: "Spanish -- Serbo Croatian", source: 82, target: 78, weight: 0.333012747324426, color: "#efefff"},
                        {desc: "Spanish -- Venetian", source: 82, target: 90, weight: 0.378975514530052, color: "#e4e4ff"},
                        {desc: "Spanish -- Danish", source: 82, target: 17, weight: 0.33340999100283, color: "#efefff"},
                        {desc: "Spanish -- Esperanto", source: 82, target: 20, weight: 0.4864910989853, color: "#cacaff"},
                        {desc: "Spanish -- Aromanian", source: 82, target: 4, weight: 0.401114218269015, color: "#dedeff"},
                        {desc: "Spanish -- English", source: 82, target: 19, weight: 0.431708810063448, color: "#d7d7ff"},
                        {desc: "Spanish -- GuernÃ©siais", source: 82, target: 29, weight: 0.40456459620677, color: "#dedeff"},
                        {desc: "Spanish -- Haitian Creole", source: 82, target: 30, weight: 0.387285716311876, color: "#e2e2ff"},
                        {desc: "Spanish -- German", source: 82, target: 26, weight: 0.344945175102009, color: "#ececff"},
                        {desc: "Spanish -- Old French", source: 82, target: 65, weight: 0.473318055165018, color: "#cdcdff"},
                        {desc: "Spanish -- Romanian", source: 82, target: 73, weight: 0.46837472375013, color: "#ceceff"},
                        {desc: "Spanish -- Maltese", source: 82, target: 53, weight: 0.414205869554214, color: "#dbdbff"},
                        {desc: "Spanish -- Polish", source: 82, target: 71, weight: 0.360131956296015, color: "#e8e8ff"},
                        {desc: "Spanish -- Catalan", source: 82, target: 10, weight: 0.752115951912714, color: "#8a8aff"},
                        {desc: "Spanish -- Asturian", source: 82, target: 5, weight: 0.799216513440985, color: "#7f7fff"},
                        {desc: "Spanish -- Basque", source: 82, target: 7, weight: 0.345409803771595, color: "#ececff"},
                        {desc: "Spanish -- Interlingua", source: 82, target: 37, weight: 0.691629653463238, color: "#9999ff"},
                        {desc: "Spanish -- Macedonian", source: 82, target: 50, weight: 0.338151768819287, color: "#eeeeff"},
                        {desc: "Spanish -- Sicilian", source: 82, target: 79, weight: 0.44143968630793, color: "#d5d5ff"},
                        {desc: "Spanish -- Bulgarian", source: 82, target: 9, weight: 0.336503830775619, color: "#eeeeff"},
                        {desc: "Spanish -- Anglo Norman", source: 82, target: 3, weight: 0.479974990536552, color: "#ccccff"},
                        {desc: "Spanish -- JÃ¨rriais", source: 82, target: 40, weight: 0.373336695523269, color: "#e5e5ff"},
                        {desc: "Spanish -- Neapolitan", source: 82, target: 59, weight: 0.447551454042459, color: "#d3d3ff"},
                        {desc: "Spanish -- Galician", source: 82, target: 25, weight: 0.846432405522142, color: "#7474ff"},
                        {desc: "Spanish -- Dalmatian", source: 82, target: 16, weight: 0.446543507913681, color: "#d4d4ff"},
                        {desc: "Spanish -- Occitan", source: 82, target: 64, weight: 0.596114657238229, color: "#b0b0ff"},
                        {desc: "Spanish -- Italian", source: 82, target: 39, weight: 0.540076962503497, color: "#bdbdff"},
                        {desc: "Afrikaans -- English", source: 1, target: 19, weight: 0.339350481069725, color: "#ededff"},
                        {desc: "Afrikaans -- Dutch", source: 1, target: 18, weight: 0.691751735461386, color: "#9999ff"},
                        {desc: "Afrikaans -- Luxembourgish", source: 1, target: 49, weight: 0.374454045478179, color: "#e5e5ff"},
                        {desc: "Afrikaans -- Norwegian", source: 1, target: 61, weight: 0.379073157586077, color: "#e4e4ff"},
                        {desc: "Afrikaans -- Limburgish", source: 1, target: 46, weight: 0.397748544651975, color: "#dfdfff"},
                        {desc: "Afrikaans -- Old Saxon", source: 1, target: 69, weight: 0.336722200388437, color: "#eeeeff"},
                        {desc: "Afrikaans -- German", source: 1, target: 26, weight: 0.458727682674333, color: "#d1d1ff"},
                        {desc: "Afrikaans -- Old High German", source: 1, target: 66, weight: 0.348744383454323, color: "#ebebff"},
                        {desc: "Afrikaans -- Swedish", source: 1, target: 84, weight: 0.41799460855727, color: "#dadaff"},
                        {desc: "Afrikaans -- West Frisian", source: 1, target: 94, weight: 0.456403821706772, color: "#d1d1ff"},
                        {desc: "Afrikaans -- Danish", source: 1, target: 17, weight: 0.38995150549108, color: "#e1e1ff"},
                        {desc: "Icelandic -- Old Norse", source: 34, target: 68, weight: 0.498635098449557, color: "#c7c7ff"},
                        {desc: "Icelandic -- Norwegian", source: 34, target: 61, weight: 0.339681666988236, color: "#ededff"},
                        {desc: "Icelandic -- Swedish", source: 34, target: 84, weight: 0.422333850446346, color: "#d9d9ff"},
                        {desc: "Icelandic -- Faroese", source: 34, target: 22, weight: 0.634726612865662, color: "#a6a6ff"},
                        {desc: "Icelandic -- Danish", source: 34, target: 17, weight: 0.384614463754934, color: "#e2e2ff"},
                        {desc: "Finnish -- Catalan", source: 23, target: 10, weight: 0.347377855549515, color: "#ebebff"},
                        {desc: "Finnish -- Estonian", source: 23, target: 21, weight: 0.518418718463343, color: "#c2c2ff"},
                        {desc: "Finnish -- Italian", source: 23, target: 39, weight: 0.335790989354366, color: "#eeeeff"},
                        {desc: "Finnish -- Esperanto", source: 23, target: 20, weight: 0.34683290446818, color: "#ebebff"},
                        {desc: "Old Saxon -- Middle English", source: 69, target: 56, weight: 0.35859030517903, color: "#e9e9ff"},
                        {desc: "Old Saxon -- Norwegian", source: 69, target: 61, weight: 0.332354890448706, color: "#efefff"},
                        {desc: "Old Saxon -- Afrikaans", source: 69, target: 1, weight: 0.336722200388437, color: "#eeeeff"},
                        {desc: "Old Saxon -- Norwegian Nynorsk", source: 69, target: 63, weight: 0.345267587349194, color: "#ececff"},
                        {desc: "Old Saxon -- Old Norse", source: 69, target: 68, weight: 0.412067327191753, color: "#dcdcff"},
                        {desc: "Old Saxon -- Old High German", source: 69, target: 66, weight: 0.580007230454361, color: "#b3b3ff"},
                        {desc: "Old Saxon -- Faroese", source: 69, target: 22, weight: 0.33936728902463, color: "#ededff"},
                        {desc: "Old Saxon -- Swedish", source: 69, target: 84, weight: 0.432320257475678, color: "#d7d7ff"},
                        {desc: "Old Saxon -- West Frisian", source: 69, target: 94, weight: 0.344988561667322, color: "#ececff"},
                        {desc: "Catalan -- Middle French", source: 10, target: 57, weight: 0.424951754457142, color: "#d9d9ff"},
                        {desc: "Catalan -- Ladino", source: 10, target: 43, weight: 0.378386121285475, color: "#e4e4ff"},
                        {desc: "Catalan -- Russian", source: 10, target: 75, weight: 0.337198976773894, color: "#eeeeff"},
                        {desc: "Catalan -- Latin", source: 10, target: 44, weight: 0.498454494841333, color: "#c7c7ff"},
                        {desc: "Catalan -- French", source: 10, target: 24, weight: 0.609209470925079, color: "#acacff"},
                        {desc: "Catalan -- Ido", source: 10, target: 35, weight: 0.443372228390196, color: "#d4d4ff"},
                        {desc: "Catalan -- Finnish", source: 10, target: 23, weight: 0.347377855549515, color: "#ebebff"},
                        {desc: "Catalan -- Romansch", source: 10, target: 74, weight: 0.421857982302991, color: "#d9d9ff"},
                        {desc: "Catalan -- Portuguese", source: 10, target: 72, weight: 0.626720254277922, color: "#a8a8ff"},
                        {desc: "Catalan -- Asturian", source: 10, target: 5, weight: 0.654380662408672, color: "#a2a2ff"},
                        {desc: "Catalan -- Swedish", source: 10, target: 84, weight: 0.357403684885824, color: "#e9e9ff"},
                        {desc: "Catalan -- Serbo Croatian", source: 10, target: 78, weight: 0.387358223173039, color: "#e2e2ff"},
                        {desc: "Catalan -- Venetian", source: 10, target: 90, weight: 0.447147775091704, color: "#d3d3ff"},
                        {desc: "Catalan -- Tarantino", source: 10, target: 86, weight: 0.36107903852315, color: "#e8e8ff"},
                        {desc: "Catalan -- Esperanto", source: 10, target: 20, weight: 0.440445112817385, color: "#d5d5ff"},
                        {desc: "Catalan -- Aromanian", source: 10, target: 4, weight: 0.384397489821863, color: "#e2e2ff"},
                        {desc: "Catalan -- English", source: 10, target: 19, weight: 0.506720563919485, color: "#c5c5ff"},
                        {desc: "Catalan -- Interlingua", source: 10, target: 37, weight: 0.50793421791851, color: "#c5c5ff"},
                        {desc: "Catalan -- Dutch", source: 10, target: 18, weight: 0.350953965886294, color: "#eaeaff"},
                        {desc: "Catalan -- Sicilian", source: 10, target: 79, weight: 0.35229503831048, color: "#eaeaff"},
                        {desc: "Catalan -- Anglo Norman", source: 10, target: 3, weight: 0.481280386319032, color: "#cbcbff"},
                        {desc: "Catalan -- GuernÃ©siais", source: 10, target: 29, weight: 0.355508124325858, color: "#e9e9ff"},
                        {desc: "Catalan -- JÃ¨rriais", source: 10, target: 40, weight: 0.365356506485688, color: "#e7e7ff"},
                        {desc: "Catalan -- German", source: 10, target: 26, weight: 0.37637926443338, color: "#e4e4ff"},
                        {desc: "Catalan -- Old French", source: 10, target: 65, weight: 0.428253822458563, color: "#d8d8ff"},
                        {desc: "Catalan -- Occitan", source: 10, target: 64, weight: 0.56894662353337, color: "#b6b6ff"},
                        {desc: "Catalan -- Dalmatian", source: 10, target: 16, weight: 0.335667811014953, color: "#eeeeff"},
                        {desc: "Catalan -- Galician", source: 10, target: 25, weight: 0.608214980710607, color: "#adadff"},
                        {desc: "Catalan -- Romanian", source: 10, target: 73, weight: 0.457184489739639, color: "#d1d1ff"},
                        {desc: "Catalan -- Italian", source: 10, target: 39, weight: 0.674964327336056, color: "#9d9dff"},
                        {desc: "Catalan -- Spanish", source: 10, target: 82, weight: 0.752115951912714, color: "#8a8aff"},
                        {desc: "Crimean Tatar -- Turkish", source: 14, target: 88, weight: 0.519702094577999, color: "#c2c2ff"},
                        {desc: "Crimean Tatar -- Russian", source: 14, target: 75, weight: 0.348812985856264, color: "#ebebff"},
                        {desc: "Crimean Tatar -- Serbo Croatian", source: 14, target: 78, weight: 0.350887868639148, color: "#eaeaff"},
                        {desc: "Crimean Tatar -- Azeri", source: 14, target: 6, weight: 0.506040210021291, color: "#c5c5ff"},
                        {desc: "Asturian -- Middle French", source: 5, target: 57, weight: 0.358784190429154, color: "#e9e9ff"},
                        {desc: "Asturian -- Ladino", source: 5, target: 43, weight: 0.478019827903103, color: "#ccccff"},
                        {desc: "Asturian -- Russian", source: 5, target: 75, weight: 0.333906512027877, color: "#efefff"},
                        {desc: "Asturian -- Latin", source: 5, target: 44, weight: 0.491378398719692, color: "#c9c9ff"},
                        {desc: "Asturian -- French", source: 5, target: 24, weight: 0.52133095824163, color: "#c2c2ff"},
                        {desc: "Asturian -- Ido", source: 5, target: 35, weight: 0.428133906075468, color: "#d8d8ff"},
                        {desc: "Asturian -- Romansch", source: 5, target: 74, weight: 0.413131493985938, color: "#dcdcff"},
                        {desc: "Asturian -- Portuguese", source: 5, target: 72, weight: 0.6507332756502, color: "#a3a3ff"},
                        {desc: "Asturian -- Catalan", source: 5, target: 10, weight: 0.654380662408672, color: "#a2a2ff"},
                        {desc: "Asturian -- Swedish", source: 5, target: 84, weight: 0.338321165237517, color: "#eeeeff"},
                        {desc: "Asturian -- Serbo Croatian", source: 5, target: 78, weight: 0.366509452156307, color: "#e7e7ff"},
                        {desc: "Asturian -- Venetian", source: 5, target: 90, weight: 0.452306314130847, color: "#d2d2ff"},
                        {desc: "Asturian -- Tarantino", source: 5, target: 86, weight: 0.376513234586105, color: "#e4e4ff"},
                        {desc: "Asturian -- Esperanto", source: 5, target: 20, weight: 0.420966818196268, color: "#dadaff"},
                        {desc: "Asturian -- Aromanian", source: 5, target: 4, weight: 0.343688439871062, color: "#ececff"},
                        {desc: "Asturian -- English", source: 5, target: 19, weight: 0.413194127926012, color: "#dcdcff"},
                        {desc: "Asturian -- Interlingua", source: 5, target: 37, weight: 0.499699329980746, color: "#c7c7ff"},
                        {desc: "Asturian -- Sicilian", source: 5, target: 79, weight: 0.409614143485115, color: "#dcdcff"},
                        {desc: "Asturian -- Anglo Norman", source: 5, target: 3, weight: 0.363178789848462, color: "#e8e8ff"},
                        {desc: "Asturian -- GuernÃ©siais", source: 5, target: 29, weight: 0.337595941995253, color: "#eeeeff"},
                        {desc: "Asturian -- JÃ¨rriais", source: 5, target: 40, weight: 0.331391268323783, color: "#efefff"},
                        {desc: "Asturian -- German", source: 5, target: 26, weight: 0.363557922848567, color: "#e7e7ff"},
                        {desc: "Asturian -- Old French", source: 5, target: 65, weight: 0.386245373707668, color: "#e2e2ff"},
                        {desc: "Asturian -- Occitan", source: 5, target: 64, weight: 0.488705196185988, color: "#c9c9ff"},
                        {desc: "Asturian -- Galician", source: 5, target: 25, weight: 0.715606232806587, color: "#9393ff"},
                        {desc: "Asturian -- Dalmatian", source: 5, target: 16, weight: 0.346819924749947, color: "#ebebff"},
                        {desc: "Asturian -- Romanian", source: 5, target: 73, weight: 0.4643798221846, color: "#cfcfff"},
                        {desc: "Asturian -- Italian", source: 5, target: 39, weight: 0.610419293892844, color: "#acacff"},
                        {desc: "Asturian -- Spanish", source: 5, target: 82, weight: 0.799216513440985, color: "#7f7fff"},
                        {desc: "Azeri -- Turkish", source: 6, target: 88, weight: 0.610738264809688, color: "#acacff"},
                        {desc: "Azeri -- Ottoman Turkish", source: 6, target: 70, weight: 0.448510953736684, color: "#d3d3ff"},
                        {desc: "Azeri -- Crimean Tatar", source: 6, target: 14, weight: 0.506040210021291, color: "#c5c5ff"},
                        {desc: "Tarantino -- Middle French", source: 86, target: 57, weight: 0.359149758304868, color: "#e9e9ff"},
                        {desc: "Tarantino -- Interlingua", source: 86, target: 37, weight: 0.492507760320698, color: "#c8c8ff"},
                        {desc: "Tarantino -- Sicilian", source: 86, target: 79, weight: 0.460305666829657, color: "#d0d0ff"},
                        {desc: "Tarantino -- Anglo Norman", source: 86, target: 3, weight: 0.377076863715079, color: "#e4e4ff"},
                        {desc: "Tarantino -- GuernÃ©siais", source: 86, target: 29, weight: 0.365581187032024, color: "#e7e7ff"},
                        {desc: "Tarantino -- JÃ¨rriais", source: 86, target: 40, weight: 0.373230740055442, color: "#e5e5ff"},
                        {desc: "Tarantino -- Portuguese", source: 86, target: 72, weight: 0.330283281927425, color: "#efefff"},
                        {desc: "Tarantino -- Old French", source: 86, target: 65, weight: 0.345521832232947, color: "#ececff"},
                        {desc: "Tarantino -- Neapolitan", source: 86, target: 59, weight: 0.504776491360231, color: "#c6c6ff"},
                        {desc: "Tarantino -- Catalan", source: 86, target: 10, weight: 0.36107903852315, color: "#e8e8ff"},
                        {desc: "Tarantino -- Asturian", source: 86, target: 5, weight: 0.376513234586105, color: "#e4e4ff"},
                        {desc: "Tarantino -- Occitan", source: 86, target: 64, weight: 0.388388155413068, color: "#e1e1ff"},
                        {desc: "Tarantino -- Galician", source: 86, target: 25, weight: 0.398478862754801, color: "#dfdfff"},
                        {desc: "Tarantino -- Venetian", source: 86, target: 90, weight: 0.461034078739191, color: "#d0d0ff"},
                        {desc: "Interlingua -- Middle French", source: 37, target: 57, weight: 0.480498232927762, color: "#cbcbff"},
                        {desc: "Interlingua -- Ladino", source: 37, target: 43, weight: 0.431935419900375, color: "#d7d7ff"},
                        {desc: "Interlingua -- Latin", source: 37, target: 44, weight: 0.589033911625544, color: "#b1b1ff"},
                        {desc: "Interlingua -- French", source: 37, target: 24, weight: 0.351188695250526, color: "#eaeaff"},
                        {desc: "Interlingua -- Ido", source: 37, target: 35, weight: 0.505173275829293, color: "#c5c5ff"},
                        {desc: "Interlingua -- Romansch", source: 37, target: 74, weight: 0.48871156623808, color: "#c9c9ff"},
                        {desc: "Interlingua -- Portuguese", source: 37, target: 72, weight: 0.626090722011797, color: "#a8a8ff"},
                        {desc: "Interlingua -- Catalan", source: 37, target: 10, weight: 0.50793421791851, color: "#c5c5ff"},
                        {desc: "Interlingua -- Asturian", source: 37, target: 5, weight: 0.499699329980746, color: "#c7c7ff"},
                        {desc: "Interlingua -- Swedish", source: 37, target: 84, weight: 0.346906777974722, color: "#ebebff"},
                        {desc: "Interlingua -- Serbo Croatian", source: 37, target: 78, weight: 0.331621492454645, color: "#efefff"},
                        {desc: "Interlingua -- Venetian", source: 37, target: 90, weight: 0.533577602499953, color: "#bfbfff"},
                        {desc: "Interlingua -- Tarantino", source: 37, target: 86, weight: 0.492507760320698, color: "#c8c8ff"},
                        {desc: "Interlingua -- Esperanto", source: 37, target: 20, weight: 0.381249446359888, color: "#e3e3ff"},
                        {desc: "Interlingua -- Aromanian", source: 37, target: 4, weight: 0.344489281531423, color: "#ececff"},
                        {desc: "Interlingua -- English", source: 37, target: 19, weight: 0.366515639399694, color: "#e7e7ff"},
                        {desc: "Interlingua -- Sicilian", source: 37, target: 79, weight: 0.479372751521298, color: "#ccccff"},
                        {desc: "Interlingua -- Anglo Norman", source: 37, target: 3, weight: 0.450176308446742, color: "#d3d3ff"},
                        {desc: "Interlingua -- GuernÃ©siais", source: 37, target: 29, weight: 0.451383208406383, color: "#d2d2ff"},
                        {desc: "Interlingua -- JÃ¨rriais", source: 37, target: 40, weight: 0.428815756809144, color: "#d8d8ff"},
                        {desc: "Interlingua -- Old French", source: 37, target: 65, weight: 0.492747635230785, color: "#c8c8ff"},
                        {desc: "Interlingua -- Neapolitan", source: 37, target: 59, weight: 0.449327000351839, color: "#d3d3ff"},
                        {desc: "Interlingua -- Occitan", source: 37, target: 64, weight: 0.563658081932573, color: "#b7b7ff"},
                        {desc: "Interlingua -- Dalmatian", source: 37, target: 16, weight: 0.383386967620071, color: "#e3e3ff"},
                        {desc: "Interlingua -- Galician", source: 37, target: 25, weight: 0.571704746022516, color: "#b5b5ff"},
                        {desc: "Interlingua -- Romanian", source: 37, target: 73, weight: 0.497723187127196, color: "#c7c7ff"},
                        {desc: "Interlingua -- Italian", source: 37, target: 39, weight: 0.72518612316894, color: "#9191ff"},
                        {desc: "Interlingua -- Spanish", source: 37, target: 82, weight: 0.691629653463238, color: "#9999ff"},
                        {desc: "Dutch -- English", source: 18, target: 19, weight: 0.348471023682566, color: "#ebebff"},
                        {desc: "Dutch -- Luxembourgish", source: 18, target: 49, weight: 0.356367716560044, color: "#e9e9ff"},
                        {desc: "Dutch -- French", source: 18, target: 24, weight: 0.350681910934928, color: "#ebebff"},
                        {desc: "Dutch -- Afrikaans", source: 18, target: 1, weight: 0.691751735461386, color: "#9999ff"},
                        {desc: "Dutch -- German", source: 18, target: 26, weight: 0.48627683008492, color: "#cacaff"},
                        {desc: "Dutch -- Catalan", source: 18, target: 10, weight: 0.350953965886294, color: "#eaeaff"},
                        {desc: "Dutch -- Swedish", source: 18, target: 84, weight: 0.377685454713245, color: "#e4e4ff"},
                        {desc: "Dutch -- Italian", source: 18, target: 39, weight: 0.34058440634943, color: "#ededff"},
                        {desc: "Dutch -- Danish", source: 18, target: 17, weight: 0.460927817513525, color: "#d0d0ff"},
                        {desc: "Norwegian BokmÃ¥l -- Old Norse", source: 62, target: 68, weight: 0.385730800174531, color: "#e2e2ff"},
                        {desc: "Norwegian BokmÃ¥l -- Norwegian Nynorsk", source: 62, target: 63, weight: 0.827502482390885, color: "#7878ff"},
                        {desc: "Norwegian BokmÃ¥l -- Swedish", source: 62, target: 84, weight: 0.672731836736953, color: "#9d9dff"},
                        {desc: "Norwegian BokmÃ¥l -- Danish", source: 62, target: 17, weight: 0.460227829338794, color: "#d0d0ff"},
                        {desc: "Sicilian -- Aromanian", source: 79, target: 4, weight: 0.364727152884006, color: "#e7e7ff"},
                        {desc: "Sicilian -- Interlingua", source: 79, target: 37, weight: 0.479372751521298, color: "#ccccff"},
                        {desc: "Sicilian -- Romansch", source: 79, target: 74, weight: 0.352881086138544, color: "#eaeaff"},
                        {desc: "Sicilian -- Catalan", source: 79, target: 10, weight: 0.35229503831048, color: "#eaeaff"},
                        {desc: "Sicilian -- Neapolitan", source: 79, target: 59, weight: 0.477033520801158, color: "#ccccff"},
                        {desc: "Sicilian -- Asturian", source: 79, target: 5, weight: 0.409614143485115, color: "#dcdcff"},
                        {desc: "Sicilian -- Dalmatian", source: 79, target: 16, weight: 0.335206082549648, color: "#eeeeff"},
                        {desc: "Sicilian -- Galician", source: 79, target: 25, weight: 0.382201689576346, color: "#e3e3ff"},
                        {desc: "Sicilian -- Occitan", source: 79, target: 64, weight: 0.383738511279402, color: "#e3e3ff"},
                        {desc: "Sicilian -- Venetian", source: 79, target: 90, weight: 0.410283904529753, color: "#dcdcff"},
                        {desc: "Sicilian -- Tarantino", source: 79, target: 86, weight: 0.460305666829657, color: "#d0d0ff"},
                        {desc: "Sicilian -- Spanish", source: 79, target: 82, weight: 0.44143968630793, color: "#d5d5ff"},
                        {desc: "Tagalog -- Hiligaynon", source: 85, target: 32, weight: 0.461845493548876, color: "#d0d0ff"},
                        {desc: "JÃ¨rriais -- Middle French", source: 40, target: 57, weight: 0.553185966164393, color: "#babaff"},
                        {desc: "JÃ¨rriais -- Latin", source: 40, target: 44, weight: 0.338189859158206, color: "#eeeeff"},
                        {desc: "JÃ¨rriais -- French", source: 40, target: 24, weight: 0.409793110774757, color: "#dcdcff"},
                        {desc: "JÃ¨rriais -- Ido", source: 40, target: 35, weight: 0.349235473800027, color: "#ebebff"},
                        {desc: "JÃ¨rriais -- Romansch", source: 40, target: 74, weight: 0.376902230565539, color: "#e4e4ff"},
                        {desc: "JÃ¨rriais -- Portuguese", source: 40, target: 72, weight: 0.351746695683239, color: "#eaeaff"},
                        {desc: "JÃ¨rriais -- Catalan", source: 40, target: 10, weight: 0.365356506485688, color: "#e7e7ff"},
                        {desc: "JÃ¨rriais -- Asturian", source: 40, target: 5, weight: 0.331391268323783, color: "#efefff"},
                        {desc: "JÃ¨rriais -- Tarantino", source: 40, target: 86, weight: 0.373230740055442, color: "#e5e5ff"},
                        {desc: "JÃ¨rriais -- Interlingua", source: 40, target: 37, weight: 0.428815756809144, color: "#d8d8ff"},
                        {desc: "JÃ¨rriais -- Anglo Norman", source: 40, target: 3, weight: 0.472992361741817, color: "#cdcdff"},
                        {desc: "JÃ¨rriais -- GuernÃ©siais", source: 40, target: 29, weight: 0.641070776168377, color: "#a5a5ff"},
                        {desc: "JÃ¨rriais -- Haitian Creole", source: 40, target: 30, weight: 0.402471905080681, color: "#dedeff"},
                        {desc: "JÃ¨rriais -- Old French", source: 40, target: 65, weight: 0.510515583573647, color: "#c4c4ff"},
                        {desc: "JÃ¨rriais -- Galician", source: 40, target: 25, weight: 0.357500514207992, color: "#e9e9ff"},
                        {desc: "JÃ¨rriais -- Occitan", source: 40, target: 64, weight: 0.412176938896829, color: "#dcdcff"},
                        {desc: "JÃ¨rriais -- Spanish", source: 40, target: 82, weight: 0.373336695523269, color: "#e5e5ff"},
                        {desc: "Neapolitan -- Interlingua", source: 59, target: 37, weight: 0.449327000351839, color: "#d3d3ff"},
                        {desc: "Neapolitan -- Sicilian", source: 59, target: 79, weight: 0.477033520801158, color: "#ccccff"},
                        {desc: "Neapolitan -- Romansch", source: 59, target: 74, weight: 0.394529865123332, color: "#e0e0ff"},
                        {desc: "Neapolitan -- Portuguese", source: 59, target: 72, weight: 0.381416287949376, color: "#e3e3ff"},
                        {desc: "Neapolitan -- Galician", source: 59, target: 25, weight: 0.361847161261503, color: "#e8e8ff"},
                        {desc: "Neapolitan -- Occitan", source: 59, target: 64, weight: 0.364107349274321, color: "#e7e7ff"},
                        {desc: "Neapolitan -- Venetian", source: 59, target: 90, weight: 0.392192127316762, color: "#e1e1ff"},
                        {desc: "Neapolitan -- Romanian", source: 59, target: 73, weight: 0.392412557179883, color: "#e1e1ff"},
                        {desc: "Neapolitan -- Spanish", source: 59, target: 82, weight: 0.447551454042459, color: "#d3d3ff"},
                        {desc: "Neapolitan -- Tarantino", source: 59, target: 86, weight: 0.504776491360231, color: "#c6c6ff"},
                        {desc: "Occitan -- Middle French", source: 64, target: 57, weight: 0.462125933605786, color: "#d0d0ff"},
                        {desc: "Occitan -- Ladino", source: 64, target: 43, weight: 0.381633242879595, color: "#e3e3ff"},
                        {desc: "Occitan -- French", source: 64, target: 24, weight: 0.340494520240559, color: "#ededff"},
                        {desc: "Occitan -- Ido", source: 64, target: 35, weight: 0.437418594504848, color: "#d6d6ff"},
                        {desc: "Occitan -- Romansch", source: 64, target: 74, weight: 0.480403134977914, color: "#cbcbff"},
                        {desc: "Occitan -- Portuguese", source: 64, target: 72, weight: 0.545520112847621, color: "#bcbcff"},
                        {desc: "Occitan -- Catalan", source: 64, target: 10, weight: 0.56894662353337, color: "#b6b6ff"},
                        {desc: "Occitan -- Asturian", source: 64, target: 5, weight: 0.488705196185988, color: "#c9c9ff"},
                        {desc: "Occitan -- Venetian", source: 64, target: 90, weight: 0.473590592711659, color: "#cdcdff"},
                        {desc: "Occitan -- Tarantino", source: 64, target: 86, weight: 0.388388155413068, color: "#e1e1ff"},
                        {desc: "Occitan -- Aromanian", source: 64, target: 4, weight: 0.367301318227061, color: "#e7e7ff"},
                        {desc: "Occitan -- Interlingua", source: 64, target: 37, weight: 0.563658081932573, color: "#b7b7ff"},
                        {desc: "Occitan -- Sicilian", source: 64, target: 79, weight: 0.383738511279402, color: "#e3e3ff"},
                        {desc: "Occitan -- Anglo Norman", source: 64, target: 3, weight: 0.451951354306526, color: "#d2d2ff"},
                        {desc: "Occitan -- GuernÃ©siais", source: 64, target: 29, weight: 0.484056164367565, color: "#cbcbff"},
                        {desc: "Occitan -- Haitian Creole", source: 64, target: 30, weight: 0.34766504363199, color: "#ebebff"},
                        {desc: "Occitan -- JÃ¨rriais", source: 64, target: 40, weight: 0.412176938896829, color: "#dcdcff"},
                        {desc: "Occitan -- Neapolitan", source: 64, target: 59, weight: 0.364107349274321, color: "#e7e7ff"},
                        {desc: "Occitan -- Old French", source: 64, target: 65, weight: 0.501420971902111, color: "#c6c6ff"},
                        {desc: "Occitan -- Galician", source: 64, target: 25, weight: 0.523220335270141, color: "#c1c1ff"},
                        {desc: "Occitan -- Dalmatian", source: 64, target: 16, weight: 0.43101599779996, color: "#d7d7ff"},
                        {desc: "Occitan -- Romanian", source: 64, target: 73, weight: 0.413145254359986, color: "#dcdcff"},
                        {desc: "Occitan -- Spanish", source: 64, target: 82, weight: 0.596114657238229, color: "#b0b0ff"},
                        {desc: "Galician -- Middle French", source: 25, target: 57, weight: 0.372313932611161, color: "#e5e5ff"},
                        {desc: "Galician -- Ladino", source: 25, target: 43, weight: 0.477221710976465, color: "#ccccff"},
                        {desc: "Galician -- Russian", source: 25, target: 75, weight: 0.347377695915928, color: "#ebebff"},
                        {desc: "Galician -- Latin", source: 25, target: 44, weight: 0.498349774686784, color: "#c7c7ff"},
                        {desc: "Galician -- French", source: 25, target: 24, weight: 0.37495786407362, color: "#e5e5ff"},
                        {desc: "Galician -- Ido", source: 25, target: 35, weight: 0.487016821119835, color: "#cacaff"},
                        {desc: "Galician -- Romansch", source: 25, target: 74, weight: 0.443124286458585, color: "#d4d4ff"},
                        {desc: "Galician -- Portuguese", source: 25, target: 72, weight: 0.776603190062558, color: "#8484ff"},
                        {desc: "Galician -- Catalan", source: 25, target: 10, weight: 0.608214980710607, color: "#adadff"},
                        {desc: "Galician -- Asturian", source: 25, target: 5, weight: 0.715606232806587, color: "#9393ff"},
                        {desc: "Galician -- Swedish", source: 25, target: 84, weight: 0.34737255408739, color: "#ebebff"},
                        {desc: "Galician -- Serbo Croatian", source: 25, target: 78, weight: 0.38442273046054, color: "#e2e2ff"},
                        {desc: "Galician -- Venetian", source: 25, target: 90, weight: 0.49472582279557, color: "#c8c8ff"},
                        {desc: "Galician -- Tarantino", source: 25, target: 86, weight: 0.398478862754801, color: "#dfdfff"},
                        {desc: "Galician -- Esperanto", source: 25, target: 20, weight: 0.428492654284028, color: "#d8d8ff"},
                        {desc: "Galician -- Aromanian", source: 25, target: 4, weight: 0.35518115774024, color: "#e9e9ff"},
                        {desc: "Galician -- English", source: 25, target: 19, weight: 0.437948925008237, color: "#d6d6ff"},
                        {desc: "Galician -- Interlingua", source: 25, target: 37, weight: 0.571704746022516, color: "#b5b5ff"},
                        {desc: "Galician -- Sicilian", source: 25, target: 79, weight: 0.382201689576346, color: "#e3e3ff"},
                        {desc: "Galician -- Anglo Norman", source: 25, target: 3, weight: 0.392213812748866, color: "#e1e1ff"},
                        {desc: "Galician -- GuernÃ©siais", source: 25, target: 29, weight: 0.333609303315481, color: "#efefff"},
                        {desc: "Galician -- JÃ¨rriais", source: 25, target: 40, weight: 0.357500514207992, color: "#e9e9ff"},
                        {desc: "Galician -- German", source: 25, target: 26, weight: 0.370212470511569, color: "#e6e6ff"},
                        {desc: "Galician -- Old French", source: 25, target: 65, weight: 0.404917401594675, color: "#dedeff"},
                        {desc: "Galician -- Neapolitan", source: 25, target: 59, weight: 0.361847161261503, color: "#e8e8ff"},
                        {desc: "Galician -- Occitan", source: 25, target: 64, weight: 0.523220335270141, color: "#c1c1ff"},
                        {desc: "Galician -- Dalmatian", source: 25, target: 16, weight: 0.377835110963687, color: "#e4e4ff"},
                        {desc: "Galician -- Romanian", source: 25, target: 73, weight: 0.471560042394903, color: "#ceceff"},
                        {desc: "Galician -- Italian", source: 25, target: 39, weight: 0.700433559130853, color: "#9797ff"},
                        {desc: "Galician -- Spanish", source: 25, target: 82, weight: 0.846432405522142, color: "#7474ff"},
                        {desc: "Scottish Gaelic -- Old Irish", source: 77, target: 67, weight: 0.572606444729963, color: "#b5b5ff"},
                        {desc: "Scottish Gaelic -- Manx", source: 77, target: 54, weight: 0.36063641209494, color: "#e8e8ff"},
                        {desc: "Scottish Gaelic -- Irish", source: 77, target: 38, weight: 0.535597927234183, color: "#bebeff"},
                        {desc: "Italian -- Latin", source: 39, target: 44, weight: 0.412094795720835, color: "#dcdcff"},
                        {desc: "Italian -- French", source: 39, target: 24, weight: 0.554104578180194, color: "#babaff"},
                        {desc: "Italian -- Ido", source: 39, target: 35, weight: 0.587809317626038, color: "#b2b2ff"},
                        {desc: "Italian -- Finnish", source: 39, target: 23, weight: 0.335790989354366, color: "#eeeeff"},
                        {desc: "Italian -- Portuguese", source: 39, target: 72, weight: 0.413870591371265, color: "#dbdbff"},
                        {desc: "Italian -- Catalan", source: 39, target: 10, weight: 0.674964327336056, color: "#9d9dff"},
                        {desc: "Italian -- Asturian", source: 39, target: 5, weight: 0.610419293892844, color: "#acacff"},
                        {desc: "Italian -- Basque", source: 39, target: 7, weight: 0.340110037494176, color: "#ededff"},
                        {desc: "Italian -- Venetian", source: 39, target: 90, weight: 0.357806351891668, color: "#e9e9ff"},
                        {desc: "Italian -- Esperanto", source: 39, target: 20, weight: 0.508025213022236, color: "#c5c5ff"},
                        {desc: "Italian -- Danish", source: 39, target: 17, weight: 0.362425141274586, color: "#e8e8ff"},
                        {desc: "Italian -- Czech", source: 39, target: 15, weight: 0.360566049747552, color: "#e8e8ff"},
                        {desc: "Italian -- Aromanian", source: 39, target: 4, weight: 0.469344559150068, color: "#ceceff"},
                        {desc: "Italian -- Interlingua", source: 39, target: 37, weight: 0.72518612316894, color: "#9191ff"},
                        {desc: "Italian -- English", source: 39, target: 19, weight: 0.467728249572366, color: "#ceceff"},
                        {desc: "Italian -- Albanian", source: 39, target: 2, weight: 0.348775832826394, color: "#ebebff"},
                        {desc: "Italian -- Dutch", source: 39, target: 18, weight: 0.34058440634943, color: "#ededff"},
                        {desc: "Italian -- Anglo Norman", source: 39, target: 3, weight: 0.502155595810561, color: "#c6c6ff"},
                        {desc: "Italian -- Bulgarian", source: 39, target: 9, weight: 0.355949732971566, color: "#e9e9ff"},
                        {desc: "Italian -- GuernÃ©siais", source: 39, target: 29, weight: 0.433114737008525, color: "#d7d7ff"},
                        {desc: "Italian -- Haitian Creole", source: 39, target: 30, weight: 0.411263448374154, color: "#dcdcff"},
                        {desc: "Italian -- German", source: 39, target: 26, weight: 0.375213213044565, color: "#e5e5ff"},
                        {desc: "Italian -- Dalmatian", source: 39, target: 16, weight: 0.488840594154889, color: "#c9c9ff"},
                        {desc: "Italian -- Galician", source: 39, target: 25, weight: 0.700433559130853, color: "#9797ff"},
                        {desc: "Italian -- Romanian", source: 39, target: 73, weight: 0.336421622686332, color: "#eeeeff"},
                        {desc: "Italian -- Spanish", source: 39, target: 82, weight: 0.540076962503497, color: "#bdbdff"},
                        {desc: "Italian -- Irish", source: 39, target: 38, weight: 0.338669487979475, color: "#ededff"}
                    ];


                    /**
                     * (c) Cotrino, 2012 (http://www.cotrino.com/)
                     * 
                     */

                    var w = 0, h = 0;
                    var chart = "network";
                    var networkChart = {
                        vis: null,
                        nodes: [],
                        labelAnchors: [],
                        labelAnchorLinks: [],
                        links: [],
                        force: null,
                        force2: null
                    };
                    var chordChart = {
                        links: [], // Square matrix
                        data: []
                    };
                    var hideUnrelated = false;
                    var similarityThresholdMin = 100;
                    var similarityThresholdMax = 0;
                    var similarityThreshold = 50;

                    function restart() {

                        if (d3.select("#graph") != null) {
                            d3.select("#graph").remove();
                        }
                        w = $('#graphHolder').width();
                        h = $('#graphHolder').height();

                        $('#similarity').html(Math.round(similarityThreshold) + "%");

                        // clear network, if available
                        if (networkChart.force != null) {
                            networkChart.force.stop();
                        }
                        if (networkChart.force2 != null) {
                            networkChart.force2.stop();
                        }
                        networkChart.nodes = [];
                        networkChart.labelAnchors = [];
                        networkChart.labelAnchorLinks = [];
                        networkChart.links = [];

                        // clear chord, if available
                        chordChart.links = [];
                        chordChart.data = [];

                        if (chart == "network") {
                            drawNetwork();
                        } else if (chart == "chord") {
                            drawChord();
                        }
                    }

                    function about() {
                        $("#about").dialog("open");
                        return false;
                    }

                    function drawNetwork() {

                        buildNetwork();

                        $("#hint").html("Move the mouse over any language to show further information or click to grab the bubble around.");

                        networkChart.vis = d3.select("#graphHolder").append("svg:svg").attr("id", "graph").attr("width", w).attr("height", h);

                        networkChart.force = d3.layout.force().size([w, h])
                                .nodes(networkChart.nodes).links(networkChart.links)
                                .gravity(1).linkDistance(100).charge(-3000)
                                .linkStrength(function (x) {
                                    return x.weight * 10
                                });
                        networkChart.force.start();

                        // brings everything towards the center of the screen
                        networkChart.force2 = d3.layout.force()
                                .nodes(networkChart.labelAnchors).links(networkChart.labelAnchorLinks)
                                .gravity(0).linkDistance(0).linkStrength(8).charge(-100).size([w, h]);
                        networkChart.force2.start();

                        var link = networkChart.vis.selectAll("line.link")
                                .data(networkChart.links).enter()
                                .append("svg:line").attr("class", "link")
                                .style("stroke", function (d, i) {
                                    return d.color
                                });

                        var node = networkChart.vis.selectAll("g.node")
                                .data(networkChart.force.nodes()).enter()
                                .append("svg:g").attr("id", function (d, i) {
                            return d.label
                        }).attr("class", "node");
                        node.append("svg:circle").attr("id", function (d, i) {
                            return "c_" + d.label
                        })
                                .attr("r", function (d, i) {
                                    return d.size
                                })
                                .style("fill", function (d, i) {
                                    return d.color
                                })
                                .style("stroke", "#FFF").style("stroke-width", 2);
                        node.call(networkChart.force.drag);
                        node.on("mouseover", function (d) {
                            showInformation(d.label);
                        });

                        var anchorLink = networkChart.vis.selectAll("line.anchorLink")
                                .data(networkChart.labelAnchorLinks);

                        var anchorNode = networkChart.vis.selectAll("g.anchorNode")
                                .data(networkChart.force2.nodes()).enter()
                                .append("svg:g").attr("class", "anchorNode");
                        anchorNode.append("svg:circle")
                                .attr("id", function (d, i) {
                                    return "ct_" + d.node.label
                                })
                                .attr("r", 0).style("fill", "#FFF");
                        anchorNode.append("svg:text")
                                .attr("id", function (d, i) {
                                    return "t_" + d.node.label
                                })
                                .text(function (d, i) {
                                    return i % 2 == 0 ? "" : d.node.label
                                }).style("fill", function (d, i) {
                            return d.node.textcolor
                        })
                                .style("font-family", "Arial")
                                .style("font-size", 10)
                                .on("mouseover", function (d) {
                                    showInformation(d.node.label);
                                });

                        var updateLink = function () {
                            this.attr("x1", function (d) {
                                return d.source.x;
                            }).attr("y1", function (d) {
                                return d.source.y;
                            }).attr("x2", function (d) {
                                return d.target.x;
                            }).attr("y2", function (d) {
                                return d.target.y;
                            });

                        }

                        var updateNode = function () {
                            this.attr("transform", function (d) {
                                return "translate(" + d.x + "," + d.y + ")";
                            });

                        }

                        networkChart.force.on("tick", function () {
                            networkChart.force2.start();
                            node.call(updateNode);
                            anchorNode.each(function (d, i) {
                                if (i % 2 == 0) {
                                    d.x = d.node.x;
                                    d.y = d.node.y;
                                } else {
                                    var b = this.childNodes[1];
                                    var diffX = d.x - d.node.x;
                                    var diffY = d.y - d.node.y;
                                    var dist = Math.sqrt(diffX * diffX + diffY * diffY);
                                    var shiftX = b.width * (diffX - dist) / (dist * 2);
                                    shiftX = Math.max(-b.width, Math.min(0, shiftX));
                                    var shiftY = 5;
                                    this.childNodes[1].setAttribute("transform", "translate(" + shiftX + "," + shiftY + ")");
                                }
                            });
                            anchorNode.call(updateNode);
                            link.call(updateLink);
                            anchorLink.call(updateLink);
                        });

                    }

                    function buildNetwork() {

                        var newMapping = [];
                        var k = 0;
                        for (var i = 0; i < nodesArray.length; i++) {
                            var node = nodesArray[i];
                            var draw = true;
                            if (hideUnrelated) {
                                if (getAmountLinks(i) == 0) {
                                    draw = false;
                                }
                            }
                            if (draw) {
                                newMapping[i] = k;
                                networkChart.nodes.push(node);
                                networkChart.labelAnchors.push({node: node});
                                networkChart.labelAnchors.push({node: node});
                                k++;
                            } else {
                                newMapping[i] = -1;
                            }
                        }

                        for (var j = 0; j < linksArray.length; j++) {
                            var link = linksArray[j];
                            var sim = link.weight;
                            adjustSlider(sim);

                            // just draw the links if similarity is higher than the threshold
                            // or the nodes exist
                            if (sim >= similarityThreshold / 100.0 && newMapping[link.source] != -1 && newMapping[link.target] != -1) {
                                var newLink = {source: newMapping[link.source], target: newMapping[link.target], weight: sim, color: link.color};
                                networkChart.links.push(newLink);
                            }
                        }

                        // link labels to circles
                        for (var i = 0; i < networkChart.nodes.length; i++) {
                            networkChart.labelAnchorLinks.push({source: i * 2, target: i * 2 + 1, weight: 1});
                        }
                    }

//adjust the scala of the slider
                    function adjustSlider(sim) {
                        if (sim * 100 > similarityThresholdMax) {
                            similarityThresholdMax = sim * 100;
                        } else if (sim * 100 < similarityThresholdMin) {
                            similarityThresholdMin = sim * 100;
                        }
                    }

                    function buildChord() {

                        var newMapping = [];
                        var k = 0;
                        for (var i = 0; i < nodesArray.length; i++) {
                            var node = nodesArray[i];
                            var draw = true;
                            if (hideUnrelated) {
                                if (getAmountLinks(i) == 0) {
                                    draw = false;
                                }
                            }
                            if (draw) {
                                newMapping[i] = k;
                                k++;
                            } else {
                                newMapping[i] = -1;
                            }
                        }

                        for (var i = 0; i < linksArray.length; i++) {
                            var link = linksArray[i];
                            var lang1 = nodesArray[link.source];
                            var lang2 = nodesArray[link.target];
                            var sim = link.weight;
                            adjustSlider(sim);

                            // just draw the links if similarity is higher than the threshold
                            // or the nodes exist
                            if (sim >= similarityThreshold / 100.0) { //&& newMapping[link.source] != -1 && newMapping[link.target] != -1 ) {
                                chordChart.data.push({
                                    source: lang1,
                                    target: lang2,
                                    size: lang1.size,
                                    similarity: sim,
                                    color: link.color
                                });
                            }
                        }
                        chordChart.data.forEach(function (d) {
                            d.source.similarity = d.similarity;
                            d.target.similarity = d.similarity;
                            d.valueOf = value; // convert object to number implicitly
                        });
                        // Initialize link matrix
                        for (var i = 0; i < nodesArray.length; i++) {
                            chordChart.links[i] = [];
                            for (var j = 0; j < nodesArray.length; j++) {
                                chordChart.links[i][j] = 0;
                            }
                        }
                        // Populate the link matrix with actual values
                        chordChart.data.forEach(function (d) {
                            chordChart.links[d.source.id][d.target.id] = d;
                        });

                        function value() {
                            return +this.size;
                        }
                    }

                    function drawChord() {

                        buildChord();

                        $("#hint").html("Move the mouse over any language to hide all others.");

                        // Chart dimensions.
                        var r1 = Math.min(w, h) / 2 - 4;
                        var r0 = r1 - 100;

                        // The chord layout, for computing the angles of chords and groups.
                        var layout = d3.layout.chord()
                                //.sortGroups(d3.descending)
                                .sortSubgroups(d3.descending)
                                .padding(.04)
                                .matrix(chordChart.links);

                        // The arc generator for the groups
                        var arc = d3.svg.arc().innerRadius(r0).outerRadius(r1);

                        // The chord generator (quadratic BÃ©zier) for the chords
                        var chord = d3.svg.chord().radius(r0);

                        // Add an SVG element
                        var svg = d3.select("#graphHolder")
                                .append("svg").attr("id", "graph")
                                .attr("width", w)
                                .attr("height", h)
                                .append("g")
                                .attr("transform", "translate(" + (100 + w / 2) + "," + h / 2 + ")");

                        // Add chords
                        svg.selectAll("path")
                                .data(layout.chords)
                                .enter().append("path")
                                .attr("class", "chord")
                                .style("fill", function (d) {
                                    return d.source.value.color;
                                })
                                .style("stroke", function (d) {
                                    return d.source.value.color;
                                })
                                .attr("d", chord);

                        // Add groups
                        var g = svg.selectAll("g.group")
                                .data(layout.groups)
                                .enter().append("g")
                                .attr("class", "group");

                        // Add the group arc
                        g.append("path")
                                .on("mouseover", fade(0))
                                .on("mouseout", fade(1))
                                .style("fill", function (d) {
                                    return nodesArray[d.index].color;
                                })
                                .attr("id", function (d, i) {
                                    return "group" + d.index;
                                })
                                .attr("d", arc);

                        // Add the language label
                        g.append("svg:text")
                                .attr("x", 6)
                                .attr("dy", 15)
                                .attr("transform", function (d) {
                                    return "rotate(" + (getMeanAgle(d) * 180 / Math.PI - 90) + ")"
                                            + "translate(" + r0 + "," + (-5 - 50 * (d.endAngle - d.startAngle)) + ")";
                                })
                                .style("fill", function (d) {
                                    return d3.rgb(nodesArray[d.index].textcolor).darker();
                                })
                                .style("font-size", function (d) {
                                    return 9 + 100 * (d.endAngle - d.startAngle);
                                })
                                .text(function (d) {
                                    return nodesArray[d.index].label;
                                });

                        function getMeanAgle(d) {
                            return d.startAngle + (d.endAngle - d.startAngle) / 2;
                        }

                        /** Returns an event handler for fading a given chord group. */
                        function fade(opacity) {
                            return function (g, i) {
                                showInformation(nodesArray[i].label);
                                svg.selectAll("path.chord")
                                        .filter(function (d) {
                                            return d.source.index != i && d.target.index != i;
                                        })
                                        .transition()
                                        .style("opacity", opacity);
                            };
                        }
                    }

                    function hide() {
                        if ($('#hide_checkbox').is(':checked')) {
                            hideUnrelated = true;
                            restart();
                        } else {
                            hideUnrelated = false;
                            restart();
                        }
                    }

                    function filterChange(event, ui) {
                        similarityThreshold = ui.value;
                        restart();
                    }

                    function chartChange(value) {
                        chart = value;
                        restart();
                    }

                    function getAmountLinks(n) {
                        var linksAmount = 0;
                        for (var j = 0; j < linksArray.length; j++) {
                            var link = linksArray[j];
                            if ((link.source == n || link.target == n) && link.weight >= similarityThreshold / 100.0) {
                                linksAmount++;
                            }
                        }
                        return linksAmount;
                    }

                    function showInformation(language) {
                        var url = "http://en.wikipedia.org/wiki/" + language + "_language";
                        var n = nodesHash[language];
                        $('#language_information').html(nodesArray[n].desc);
                    }

                    $(function () {
                        $("#about").dialog({
                            autoOpen: false,
                            show: "blind",
                            hide: "explode",
                            width: 800,
                            height: 600
                        });
                        $("#chartSelector").buttonset();
                        $("#chartSelector").change(function (event) {
                            chartChange($("input[type=radio]:checked").val());
                        });
                        restart();
                        $("#slider").slider({change: filterChange, min: similarityThresholdMin, max: similarityThresholdMax, value: similarityThreshold});
                    });

                }]);
})();
