%require "3.8.2"
%language "c++"

%define lr.type canonical-lr

%define api.value.type variant
%define api.token.constructor
%define parse.assert


%nterm <std::string> S
%nterm <std::string> A
%nterm <std::string> B
%nterm <std::string> C

%token <std::string> CH_A
%token <std::string> CH_B
%token <std::string> CH_C
%token <std::string> CH_D

%code {
 namespace yy {
   auto yylex() -> parser::symbol_type {
     std::string str;
     std::cin >> str;
     for(const char &c : str){
       switch (c){
         case 'a':
           return parser::make_CH_A(str);
         case 'b':
           return parser::make_CH_B(str);
         case 'c':
           return parser::make_CH_C(str);
         case 'd':
           return parser::make_CH_D(str);
         default:
           return parser::make_YYEOF();
       }
     }
   }
 }
}

%%

input:
  %empty
| input S { std::cout << "--> " << $2 << "\n"; }
;

S:
  CH_C A { $$ = $1 + $2; }
| B CH_A { $$ = $1 + $2; }
| CH_A
;

A:
  CH_A B CH_A { $$ = $1 + $2 + $3; }
| CH_B B CH_B { $$ = $1 + $2 + $3; }
| CH_A CH_A { $$ = $1 + $2; }
| CH_B CH_B { $$ = $1 + $2; }
;

B:
  CH_D C CH_D { $$ = $1 + $2 + $3; }
;

C:
  CH_C A CH_C { $$ = $1 + $2 + $3; }
;

%%

namespace yy {
  auto parser::error(const std::string &msg) -> void {
    std::cerr << msg << '\n';
  }
}

int main(){
  yy::parser p;
  return p.parse();
}
