function showAlert() {
    alert("The button was clicked!");
}
function ExpandOrCollapse(element) {
    element.expandOrCollapse();
}
function goToReportPage() {
    var genreSelector = document.getElementById('genreSelector');
    var genre = genreSelector.options[genreSelector.selectedIndex].value;
    if (genre) {
        window.location.href = '/books/report/' + genre;
    } else {
        alert('Proszę wybrać gatunek.');
    }
}
