const moviesList = document.getElementById('moviesList')
const searchBar = document.getElementById('searchInput')

const allMovies = [];

fetch("http://localhost:8080/movies/api").
then(response => response.json()).
then(data => {
    for (let movie of data) {
        allMovies.push(movie);
    }
})

console.log(allMovies);


searchBar.addEventListener('keyup', (e) => {
    const searchingCharacters = searchBar.value.toLowerCase();
    let filteredMovies = allMovies.filter(movie => {
        return movie.name.toLowerCase().includes(searchingCharacters)
    });
    displayMovies(filteredMovies);
})



const displayMovies = (movies) => {
    moviesList.innerHTML = movies
        .map((m) => {
            return `
                <div class="post">
                    <img class="movie-home-images" src="${m.imageUrl}" alt="${m.name}">
                    <a class="movie-name" type="button" href="/movies/movie-page-details/${m.id}">${m.name}</a>
                    <p class="post_info" > ${m.genre}, ${m.genre2}| ${m.duration} minutes</p>
                </div>
            `
        })
        .join('');

}
