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
