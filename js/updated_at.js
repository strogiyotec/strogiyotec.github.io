const span = document.getElementById("lastupdated")
const github_api_url = "https://api.github.com/repos/strogiyotec/strogiyotec.github.io/commits?sha=master"
fetch(github_api_url)
    .then((response) => {
        return response.json()
    })
    .then((data) => {
        const lastUpdated = new Date(data[0].commit.committer.date);
        span.textContent += lastUpdated.getDate() + "/" + (lastUpdated.getMonth() + 1) + "/" + lastUpdated.getFullYear();

    })
    .catch(() => {
        const lastUpdated = new Date();
        span.textContent += lastUpdated.getDate() + "/" + (lastUpdated.getMonth() + 1) + "/" + lastUpdated.getFullYear();
    })
