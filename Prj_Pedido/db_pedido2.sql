-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 07/10/2024 às 07:49
-- Versão do servidor: 10.4.32-MariaDB
-- Versão do PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `db_pedido2`
--

DELIMITER $$
--
-- Procedimentos
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `Proc_AtualizarCliente` (IN `V_A01_id` INT, IN `V_A01_NOME` VARCHAR(50), IN `V_A01_ENDERECO` VARCHAR(50), IN `V_A01_TELEFONE` VARCHAR(11), IN `V_A01_CREDITO` DOUBLE, IN `V_A01_CPF` VARCHAR(11))   BEGIN
		UPDATE cliente_01
		SET A01_NOME = V_A01_NOME, A01_ENDERECO = V_A01_ENDERECO, A01_TELEFONE = V_A01_TELEFONE, A01_CREDITO = V_A01_CREDITO, A01_CPF = V_A01_CPF
		WHERE A01_CODIGO = V_A01_id;
	END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `Proc_AtualizarItem` (IN `V_A04_id` INT, IN `V_A02_id` INT, IN `V_A03_id` INT, IN `V_A04_quantidade` INT, IN `V_A04_valor_item` DECIMAL(10,2))   BEGIN
		UPDATE item_04
		SET A02_CODIGO = V_A02_id, A03_CODIGO = V_A03_id, A04_quantidade = V_A04_quantidade, A04_valor_item = V_A04_valor_item
		WHERE A04_CODIGO = V_A04_id;
	END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `Proc_AtualizarPedido` (IN `V_A02_id` INT, IN `V_A02_data` DATE, IN `V_A02_valor_total` DECIMAL(10,2), IN `V_A01_id` INT)   BEGIN
		UPDATE pedido_02
		SET A01_CODIGO = V_A01_id, A02_data = V_A02_data, A02_valor_total = V_A02_valor_total
		WHERE A02_CODIGO = V_A02_id;
	END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `Proc_AtualizarPedido_SD` (IN `V_A02_id` INT, IN `V_A02_valor_total` DECIMAL(10,2), IN `V_A01_id` INT)   BEGIN
		UPDATE pedido_02
		SET A01_CODIGO = V_A01_id, A02_valor_total = V_A02_valor_total
		WHERE A02_CODIGO = V_A02_id;
	END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `Proc_AtualizarProduto` (IN `V_A03_id` INT, IN `V_A03_descricao` VARCHAR(250), IN `V_A03_valor_unitario` DECIMAL(10,2), IN `V_A03_estoque` INT)   BEGIN
		UPDATE produto_03
		SET A03_descricao = V_A03_descricao, A03_valor_unitario = V_A03_valor_unitario, A03_estoque = V_A03_estoque
		WHERE A03_CODIGO = V_A03_id;
	END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `Proc_DeletarCliente` (IN `V_A01_id` INT)   BEGIN
		DELETE FROM cliente_01 where A01_CODIGO = V_A01_id;
	END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `Proc_DeletarItem` (IN `V_A04_id` INT)   BEGIN 
		DELETE FROM item_04 WHERE A04_CODIGO = V_A04_id;
	END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `Proc_DeletarPedido` (IN `V_A02_id` INT)   BEGIN
		DELETE FROM pedido_02 where A02_CODIGO = V_A02_id;
	END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `Proc_DeletarProduto` (IN `V_A03_id` INT)   BEGIN
		DELETE FROM produto_03 where A03_CODIGO = V_A03_id;
	END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `Proc_InsCliente_AI` (IN `V_A01_nome` VARCHAR(50), IN `V_A01_endereco` VARCHAR(50), IN `V_A01_telefone` CHAR(11), IN `V_A01_cpf` CHAR(11), IN `V_A01_credito` DECIMAL(10,2))   BEGIN 
    DECLARE V_id INTEGER;
    SELECT MAX(A01_codigo) INTO V_id FROM CLIENTE_01;
 
 
    IF V_id IS NULL THEN
    SET V_id = 1;
ELSE
    SET V_id = V_id + 1;
END IF;
 
 
    INSERT INTO CLIENTE_01(A01_codigo, A01_nome, A01_endereco, A01_telefone, A01_cpf,A01_credito)
    VALUES (V_id, V_A01_nome, V_A01_endereco, V_A01_telefone, V_A01_cpf, V_A01_credito);
 
	COMMIT;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `Proc_InsItem_AI` (IN `V_A02_codigo` INT, IN `V_A03_codigo` INT, IN `V_A04_quantidade` INT)   BEGIN
    DECLARE V_id INTEGER;
    DECLARE V_A04_valor_item DECIMAL(10,2);

    -- Seleciona o maior código atual da tabela ITEM_04
    SELECT MAX(A04_codigo) INTO V_id FROM ITEM_04;

    -- Verifica se o código retornado é NULL e define o código inicial ou o próximo valor
    IF V_id IS NULL THEN
        SET V_id = 1;
    ELSE
        SET V_id = V_id + 1;
    END IF;

    -- Puxa o valor unitário do produto da tabela PRODUTO_03
    SELECT A03_valor_unitario INTO V_A04_valor_item FROM PRODUTO_03 WHERE A03_codigo = V_A03_codigo;

    -- Insere um novo registro na tabela ITEM_04
    INSERT INTO ITEM_04 (A04_codigo, A02_codigo, A03_codigo, A04_quantidade, A04_valor_item)
    VALUES (V_id, V_A02_codigo, V_A03_codigo, V_A04_quantidade, V_A04_valor_item);
    
    -- Atualiza o estoque do produto na tabela PRODUTO_03
    UPDATE PRODUTO_03
    SET A03_estoque = A03_estoque - V_A04_quantidade
    WHERE A03_codigo = V_A03_codigo;

    -- Atualiza o valor total do pedido na tabela PEDIDO_02
    UPDATE PEDIDO_02
    SET A02_valor_total = A02_valor_total + (V_A04_quantidade * V_A04_valor_item)
    WHERE A02_codigo = V_A02_codigo;

    -- Realiza o commit das alterações
    COMMIT;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `Proc_InsPedido_AI` (IN `A01_codigo` INT)   BEGIN
    DECLARE V_id INTEGER;
    SELECT MAX(A02_codigo) INTO V_id FROM PEDIDO_02;
 
    IF V_id IS NULL THEN
        SET V_id = 1;
    ELSE
        SET V_id = V_id + 1;
    END IF;
 
    INSERT INTO PEDIDO_02(A02_codigo, A02_data, A02_valor_total, A01_codigo)
    VALUES (V_id, CURDATE(), 0, A01_codigo);
 
    COMMIT;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `Proc_InsProduto_AI` (IN `V_A03_descricao` VARCHAR(250), IN `V_A03_valor_unitario` DECIMAL(10,2), IN `V_A03_estoque` INT)   BEGIN 

    DECLARE V_id INTEGER;
    SELECT MAX(A03_codigo) INTO V_id FROM PRODUTO_03;


    IF V_id IS NULL THEN
	SET V_id = 1;
    ELSE
	SET V_id = V_id + 1;
    END IF;

    INSERT INTO PRODUTO_03(A03_codigo, A03_descricao, A03_valor_unitario, A03_estoque)
    VALUES (V_id, V_A03_descricao, V_A03_valor_unitario, V_A03_estoque);

	COMMIT;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `Proc_LerCliente` (IN `V_A01_id` INT)   BEGIN
		SELECT * FROM cliente_01 where A01_CODIGO = V_A01_id;
	END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `Proc_LerItem` (IN `V_A04_id` INT)   BEGIN 
		SELECT * FROM item_04 WHERE A04_CODIGO = V_A04_id;
	END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `Proc_LerPedido` (IN `V_A02_id` INT)   BEGIN
		SELECT * FROM pedido_02 where A02_CODIGO = V_A02_id;
	END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `Proc_LerProduto` (IN `V_A03_id` INT)   BEGIN
		SELECT * FROM produto_03 where A03_CODIGO = V_A03_id;
	END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `Proc_LerTodosCliente` ()   BEGIN
		SELECT * FROM cliente_01;
	END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `Proc_LerTodosItem` ()   BEGIN 
		SELECT * FROM item_04;
	END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `Proc_LerTodosPedido` ()   BEGIN
		SELECT * FROM pedido_02;
	END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `Proc_LerTodosProduto` ()   BEGIN
		SELECT * FROM produto_03;
	END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Estrutura para tabela `cliente_01`
--

CREATE TABLE `cliente_01` (
  `A01_CODIGO` int(11) NOT NULL,
  `A01_NOME` varchar(50) NOT NULL,
  `A01_ENDERECO` varchar(50) DEFAULT NULL,
  `A01_TELEFONE` varchar(11) NOT NULL,
  `A01_CREDITO` double DEFAULT NULL,
  `A01_CPF` varchar(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `cliente_01`
--

INSERT INTO `cliente_01` (`A01_CODIGO`, `A01_NOME`, `A01_ENDERECO`, `A01_TELEFONE`, `A01_CREDITO`, `A01_CPF`) VALUES
(1, 'Marco Freitas', 'Rua Delfim Costa 108', '11945797799', 13000, '52179853880'),
(2, 'Mateus Santos de Freitas', 'Rua Delfim costa', '11945794799', 10000, '52178169880');

-- --------------------------------------------------------

--
-- Estrutura para tabela `item_04`
--

CREATE TABLE `item_04` (
  `A04_codigo` int(11) NOT NULL,
  `A02_codigo` int(11) NOT NULL,
  `A03_codigo` int(11) NOT NULL,
  `A04_quantidade` int(11) NOT NULL,
  `A04_valor_item` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `item_04`
--

INSERT INTO `item_04` (`A04_codigo`, `A02_codigo`, `A03_codigo`, `A04_quantidade`, `A04_valor_item`) VALUES
(1, 2, 1, 15, 2.00),
(2, 1, 1, 10, 3.00),
(3, 3, 3, 10, 10.00),
(4, 3, 2, 12, 4.00);

-- --------------------------------------------------------

--
-- Estrutura para tabela `pedido_02`
--

CREATE TABLE `pedido_02` (
  `A02_codigo` int(11) NOT NULL,
  `A02_data` date NOT NULL,
  `A02_valor_total` decimal(10,2) NOT NULL,
  `A01_codigo` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `pedido_02`
--

INSERT INTO `pedido_02` (`A02_codigo`, `A02_data`, `A02_valor_total`, `A01_codigo`) VALUES
(1, '2024-09-28', 33.00, 1),
(2, '2024-09-28', 39.00, 2),
(3, '2024-09-28', 148.00, 2);

-- --------------------------------------------------------

--
-- Estrutura para tabela `produto_03`
--

CREATE TABLE `produto_03` (
  `A03_codigo` int(11) NOT NULL,
  `A03_descricao` varchar(250) NOT NULL,
  `A03_valor_unitario` decimal(10,2) NOT NULL,
  `A03_estoque` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `produto_03`
--

INSERT INTO `produto_03` (`A03_codigo`, `A03_descricao`, `A03_valor_unitario`, `A03_estoque`) VALUES
(1, 'Pera', 3.00, 31),
(2, 'Maça', 4.00, 88),
(3, 'Chocolate', 7.00, 30),
(4, 'Paçoca', 1.75, 50),
(5, 'Amendoim', 4.00, 20);

--
-- Índices para tabelas despejadas
--

--
-- Índices de tabela `cliente_01`
--
ALTER TABLE `cliente_01`
  ADD PRIMARY KEY (`A01_CODIGO`);

--
-- Índices de tabela `item_04`
--
ALTER TABLE `item_04`
  ADD PRIMARY KEY (`A04_codigo`),
  ADD KEY `A02_codigo` (`A02_codigo`),
  ADD KEY `A03_codigo` (`A03_codigo`);

--
-- Índices de tabela `pedido_02`
--
ALTER TABLE `pedido_02`
  ADD PRIMARY KEY (`A02_codigo`),
  ADD KEY `A01_codigo` (`A01_codigo`);

--
-- Índices de tabela `produto_03`
--
ALTER TABLE `produto_03`
  ADD PRIMARY KEY (`A03_codigo`);

--
-- Restrições para tabelas despejadas
--

--
-- Restrições para tabelas `item_04`
--
ALTER TABLE `item_04`
  ADD CONSTRAINT `item_04_ibfk_1` FOREIGN KEY (`A02_codigo`) REFERENCES `pedido_02` (`A02_codigo`),
  ADD CONSTRAINT `item_04_ibfk_2` FOREIGN KEY (`A03_codigo`) REFERENCES `produto_03` (`A03_codigo`);

--
-- Restrições para tabelas `pedido_02`
--
ALTER TABLE `pedido_02`
  ADD CONSTRAINT `pedido_02_ibfk_1` FOREIGN KEY (`A01_codigo`) REFERENCES `cliente_01` (`A01_CODIGO`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
