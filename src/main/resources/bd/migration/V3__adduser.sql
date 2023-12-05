CREATE TABLE "usuario"
(
  	"id_usuario" UUID NOT NULL,
	"usuario" Character varying(80) NOT NULL,
	"senha" Character varying(80) NOT NULL
);
ALTER TABLE "usuario" ADD CONSTRAINT "pk_usuario" PRIMARY KEY ("id_usuario")
;