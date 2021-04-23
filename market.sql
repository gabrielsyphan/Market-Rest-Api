-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Tempo de geração: 23/04/2021 às 20:55
-- Versão do servidor: 10.4.18-MariaDB
-- Versão do PHP: 8.0.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `market`
--

-- --------------------------------------------------------

--
-- Estrutura para tabela `category`
--

CREATE TABLE `category` (
  `id` int(11) NOT NULL,
  `name` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Despejando dados para a tabela `category`
--

INSERT INTO `category` (`id`, `name`) VALUES
(1, 'Roupas'),
(2, 'Cosméticos'),
(3, 'Alimentos'),
(4, 'Eletrônicos'),
(7, 'Calçados');

-- --------------------------------------------------------

--
-- Estrutura para tabela `coupon`
--

CREATE TABLE `coupon` (
  `id` int(11) NOT NULL,
  `code` char(6) DEFAULT NULL,
  `discount` decimal(7,2) DEFAULT NULL,
  `total_value` decimal(7,2) DEFAULT NULL,
  `discount_type_id` int(11) DEFAULT NULL,
  `category_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Despejando dados para a tabela `coupon`
--

INSERT INTO `coupon` (`id`, `code`, `discount`, `total_value`, `discount_type_id`, `category_id`) VALUES
(14, 'ODT234', '0.20', '0.00', 5, 0),
(18, 'MKTA10', '50.00', '4500.00', 6, 4);

-- --------------------------------------------------------

--
-- Estrutura para tabela `coupon_category`
--

CREATE TABLE `coupon_category` (
  `id` int(11) NOT NULL,
  `category_id` int(11) NOT NULL,
  `coupon_id` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura para tabela `discount_type`
--

CREATE TABLE `discount_type` (
  `id` int(11) NOT NULL,
  `name` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Despejando dados para a tabela `discount_type`
--

INSERT INTO `discount_type` (`id`, `name`) VALUES
(5, 'Porcentagem'),
(6, 'Valor');

-- --------------------------------------------------------

--
-- Estrutura para tabela `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Despejando dados para a tabela `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(26);

-- --------------------------------------------------------

--
-- Estrutura para tabela `product`
--

CREATE TABLE `product` (
  `id` int(11) NOT NULL,
  `category_id` int(11) DEFAULT NULL,
  `name` varchar(30) DEFAULT NULL,
  `value` decimal(7,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Despejando dados para a tabela `product`
--

INSERT INTO `product` (`id`, `category_id`, `name`, `value`) VALUES
(22, 4, 'Camera Canon T100', '2100.00');

-- --------------------------------------------------------

--
-- Estrutura para tabela `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `email` varchar(60) DEFAULT NULL,
  `password` varchar(120) DEFAULT NULL,
  `type_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Despejando dados para a tabela `user`
--

INSERT INTO `user` (`id`, `email`, `password`, `type_id`) VALUES
(1, 'gabriel@syphan.com.br', '$2a$10$tPpIUXVfLLmL0B0A7SWwTOC4p2H8DlTNH7n8ZApsz4TbqP0YZ1lNq', 1),
(3, 'lucasgabrielpdoliveira@gmail.com', '$2a$10$tPpIUXVfLLmL0B0A7SWwTOC4p2H8DlTNH7n8ZApsz4TbqP0YZ1lNq', 2);

-- --------------------------------------------------------

--
-- Estrutura para tabela `user_type`
--

CREATE TABLE `user_type` (
  `id` int(11) NOT NULL,
  `name` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Despejando dados para a tabela `user_type`
--

INSERT INTO `user_type` (`id`, `name`) VALUES
(1, 'Administrador'),
(2, 'Cliente');

--
-- Índices para tabelas despejadas
--

--
-- Índices de tabela `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`id`);

--
-- Índices de tabela `coupon`
--
ALTER TABLE `coupon`
  ADD PRIMARY KEY (`id`),
  ADD KEY `discount_type_id` (`discount_type_id`);

--
-- Índices de tabela `coupon_category`
--
ALTER TABLE `coupon_category`
  ADD PRIMARY KEY (`id`);

--
-- Índices de tabela `discount_type`
--
ALTER TABLE `discount_type`
  ADD PRIMARY KEY (`id`);

--
-- Índices de tabela `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id`),
  ADD KEY `category_id` (`category_id`);

--
-- Índices de tabela `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD KEY `type_id` (`type_id`);

--
-- Índices de tabela `user_type`
--
ALTER TABLE `user_type`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT para tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `category`
--
ALTER TABLE `category`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de tabela `coupon`
--
ALTER TABLE `coupon`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT de tabela `discount_type`
--
ALTER TABLE `discount_type`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de tabela `product`
--
ALTER TABLE `product`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- AUTO_INCREMENT de tabela `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de tabela `user_type`
--
ALTER TABLE `user_type`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Restrições para tabelas despejadas
--

--
-- Restrições para tabelas `coupon`
--
ALTER TABLE `coupon`
  ADD CONSTRAINT `coupon_ibfk_1` FOREIGN KEY (`discount_type_id`) REFERENCES `discount_type` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION;

--
-- Restrições para tabelas `product`
--
ALTER TABLE `product`
  ADD CONSTRAINT `product_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION;

--
-- Restrições para tabelas `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `user_ibfk_1` FOREIGN KEY (`type_id`) REFERENCES `user_type` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
