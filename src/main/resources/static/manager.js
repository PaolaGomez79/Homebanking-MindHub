Vue.createApp({
    data() {
        return {
            email: "",
            firstName: "",
            lastName: "",
            outPut: "",
            clients: [],
            loading:false
        }
    },
    created() {
        this.loadData();
    },
    methods: {
        // load and display JSON sent by server for /clients
        async loadData() {
            try {
                this.loading= true;
                const response = await axios.get("/api/clients");
                console.log(response)
                this.outPut = response.data;
                 console.log(response.data)
                //this.clients = response.data._embedded.clients;
                this.clients = response.data;
                console.log(response.data._embedded.clients)
            } catch(error) {
                console.log("Error loading clients: " + error);
                //alert("Error loading clients: " + error);
            } finally {
                this.loading = false; // Ocultar el estado de carga
            }
            /*axios.get("/api/clients")
                .then((response) => {
                    // handle success
                    this.outPut = response.data;
                    this.clients = response.data._embedded.clients;
                })*/
               /* .catch((error) => {
                    alert("Error loading clients: " + error)
                })*/
        },
        // handler for when user clicks add client
        addClient() {
            if (this.email.length > 1 && this.firstName.length > 1 && this.lastName.length > 1) {
                this.postClient(this.email, this.firstName, this.lastName);
            }
        },
        // code to post a new client using AJAX
        // on success, reload and display the updated data from the server
        postClient(email, firstName, lastName) {
            axios.post("/api/clients", { "email": email, "firstName": firstName, "lastName": lastName })
                .then((response) => {
                    // handle success
                    this.loadData();
                    this.clearData();
                })
                .catch((error) => {
                    // handle error
                    alert("Error to create client: " + error)
                })
        },
        clearData() {
            this.firstName = "";
            this.lastName = "";
            this.email = "";
        }
    }
}).mount("#app");