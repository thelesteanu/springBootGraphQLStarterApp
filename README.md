# springBootGraphQLStarterApp
Demo application of a Spring Boot GraphQL Starter integration






Example queries:






###Queries

# query{
#   authors{
#     id
#   }

#   authorById(id: 1){
#     id
#     name
#     books {
#       id
#       title
#       publisher
#     }
#   # bestSellerNumber
#   }

#   # findAwesomeBookById(id:1) {
#   #   id
#   # }
#   # findAwesomeBookByTitle(title:"Dancing in the moonlight"){
#   #   id
#   # }

# }









### Mutations

# mutation{
#   addBook(book:{
#     title:"Problems in paradise"
#     publisher:"Diverta"
#     authorId: "1"
#   }){
#     id
#   }
# }














# ### Reactive
# query {
#   greeting
#   greetingMono
#   greetingsFlux
# }

### Web sockets
# subscription {
#   greetings
# }



