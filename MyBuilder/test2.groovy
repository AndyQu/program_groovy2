import MyXmlBuilder

langs = [
    'C++' : 'Stroustrup', 
    'Java' : 'Gosling', 
    'Lisp' : 'McCarthy',
    'Lua'   :'Andy Qu'
]
/*
xmlDocument = new MyXmlBuilder().bind { 
    mkp.xmlDeclaration()
    mkp.declareNamespace(computer: "Computer")
    languages {
        comment << "Created using StreamingMarkupBuilder" 
        langs.each { 
            key, value ->
                computer.language(name: key) {
                    author  (value)
            }
        }
    }
}
println xmlDocument
*/
builder=new MyXmlBuilder()
builder.languages {
        //comment << "Created using StreamingMarkupBuilder" 
        langs.each { 
            key, value ->
                language(name: key) {
                    author(value)
            }
        }
    }