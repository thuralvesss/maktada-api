package br.com.maktaba.model;  //

import jakarta.persistence.*;   //  2. IMPORT JPA (banco)
import lombok.Data;             //  3. IMPORT LOMBOK (getters grátis)
import lombok.NoArgsConstructor; //  4. Construtor vazio
import lombok.AllArgsConstructor;// 5. Construtor completo

@Entity                    //  6. JPA: "isso é tabela no banco"
@Table(name = "usuarios")  //  7. Nome da tabela = "usuarios"
@Data                      //  8. LOMBOK: cria get/set/toString
@NoArgsConstructor        //  9. Construtor vazio (JPA precisa)
@AllArgsConstructor       // 10. Construtor com todos campos
public class Usuario {     //  11. NOME CLASSE = nome tabela Java

    @Id                           //  12. "Este é ID principal"
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //  13. ID auto++ (1,2,3...)
    private Long id;              //  14. Campo ID (bigint)

    private String nome;          // 15. Campo nome (varchar)

    @Column(unique = true)        //  16. "Email ÚNICO no banco"
    private String email;         //  17. Campo email (varchar UNIQUE)

    @Column(unique = true)
    private String username;      //  18. Username ÚNICO

    private String senhaHash;     //  19. Senha (depois criptografamos)

    private Integer idade;        // 20. Idade (int)

    @Column(columnDefinition = "TEXT")  //  21. Campo LONGO texto
    private String interessesLiterarios; //  22. RF01 documento
}
