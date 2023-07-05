const achatBtn = document.getElementById('achat-btn');
const venteBtn = document.getElementById('vente-btn');

function updateVenteBtn() {
    if (achatBtn.checked) {
        document.getElementById('enchOuverte').disabled  = false;
        document.getElementById('enchEnCours').disabled  = false;
        document.getElementById('enchRemporte').disabled  = false;

        document.getElementById('venteEnCours').disabled  = true;
        document.getElementById('venteNonDebuté').disabled  = true;
        document.getElementById('venteNonTerminé').disabled  = true;
    }

    if (venteBtn.checked) {
        document.getElementById('venteEnCours').disabled  = false;
        document.getElementById('venteNonDebuté').disabled  = false;
        document.getElementById('venteNonTerminé').disabled  = false;

        document.getElementById('enchOuverte').disabled  = true;
        document.getElementById('enchEnCours').disabled  = true;
        document.getElementById('enchRemporte').disabled  = true;
    }
}
updateVenteBtn();

achatBtn.addEventListener('change', updateVenteBtn);
venteBtn.addEventListener('change', updateVenteBtn);


document.getElementById("enchOuverte").addEventListener("change", rechercher);
document.getElementById("enchEnCours").addEventListener("change", rechercher);
document.getElementById("enchRemporte").addEventListener("change", rechercher);

document.getElementById("venteEnCours").addEventListener("change", rechercher);
document.getElementById("venteNonDebute").addEventListener("change", rechercher);
document.getElementById("venteNonTermine").addEventListener("change", rechercher);

function rechercher() {
   /* let filtreAchat = document.getElementById("achat-btn").checked;*/
    let enchOuverte = document.getElementById("enchOuverte").checked;
    let enchEnCours = document.getElementById("enchEnCours").checked;
    let enchRemporte = document.getElementById("enchRemporte").checked;

    let venteEnCours = document.getElementById("venteEnCours").checked;
    let venteNonDebute = document.getElementById("venteNonDebuté").checked;
    let venteNonTermine = document.getElementById("venteNonTerminé").checked;

    let searchActionUrl = document.getElementById("searchActionUrl").value;

   /* if (filtreAchat) {
        // Effectuer la requête pour les articles à acheter
        window.location.href = searchActionUrl + "?type=achat";
    }*/

    if (enchOuverte) {
        // Effectuer la requête pour les enchères ouvertes
        window.location.href = searchActionUrl + "?type=enchOuverte";
    }

    if (enchEnCours) {
        // Effectuer la requête pour les enchères en cours
        window.location.href = searchActionUrl + "?type=enchEnCours";
    }

    if (enchRemporte) {
        // Effectuer la requête pour les enchères remportées
        window.location.href = searchActionUrl + "?type=enchRemporte";
    }

    if (venteEnCours) {
        window.location.href = searchActionUrl + "?type=venteEnCours";
    }

    if (venteNonDebute) {
        window.location.href = searchActionUrl + "?type=venteNonDebute";
    }

    if (venteNonTermine) {
        window.location.href = searchActionUrl + "?type=venteEnCours";
    }
}
