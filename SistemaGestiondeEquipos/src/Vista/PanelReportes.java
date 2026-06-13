package Vista;

import Dao.EquipoDAO;
import Dao.PrestamoDao;
import Dao.SolicitanteDao;
import Modelo.Equipo;
import Modelo.Prestamo;
import Modelo.Solicitante;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class PanelReportes extends JPanel {

    private static final long serialVersionUID = 1L;

    public PanelReportes() {
        setLayout(new BorderLayout(0, 0));

        // PANEL TÍTULO =====================================================
        JPanel panelTitulo = new JPanel();
        panelTitulo.setBackground(Color.WHITE);
        panelTitulo.setPreferredSize(new Dimension(0, 50));
        panelTitulo.setBorder(new MatteBorder(0, 0, 1, 0, new Color(220, 220, 220)));
        panelTitulo.setLayout(new BorderLayout());
        add(panelTitulo, BorderLayout.NORTH);

        JLabel lblTitulo = new JLabel("Reportes del Sistema");
        lblTitulo.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 18));
        lblTitulo.setForeground(new Color(20, 20, 20));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(0, 16, 0, 0));
        panelTitulo.add(lblTitulo, BorderLayout.CENTER);

        // PANEL PRINCIPAL ==================================================
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new GridLayout(2, 1, 0, 20));
        panelPrincipal.setBackground(new Color(245, 247, 250));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(panelPrincipal, BorderLayout.CENTER);

        // CARD REPORTE 1 — PRÉSTAMOS ACTIVOS ================================
        JPanel cardPrestamos = new JPanel();
        cardPrestamos.setLayout(new BorderLayout(0, 10));
        cardPrestamos.setBackground(Color.WHITE);
        cardPrestamos.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(210, 215, 220)),
                BorderFactory.createEmptyBorder(16, 16, 16, 16)));
        panelPrincipal.add(cardPrestamos);

        JLabel lblTituloPrestamos = new JLabel("Reporte de Préstamos Activos");
        lblTituloPrestamos.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14));
        lblTituloPrestamos.setForeground(new Color(20, 20, 20));
        cardPrestamos.add(lblTituloPrestamos, BorderLayout.NORTH);

        JLabel lblDescPrestamos = new JLabel("<html>Genera un reporte en PDF con todos los préstamos activos,<br>incluyendo solicitante, equipo, fechas y estado.</html>");
        lblDescPrestamos.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 12));
        lblDescPrestamos.setForeground(new Color(90, 90, 90));
        cardPrestamos.add(lblDescPrestamos, BorderLayout.CENTER);

        JButton btnReportePrestamos = new JButton("Generar Reporte PDF");
        btnReportePrestamos.setBackground(new Color(25, 118, 210));
        btnReportePrestamos.setForeground(Color.WHITE);
        btnReportePrestamos.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12));
        btnReportePrestamos.setFocusPainted(false);
        btnReportePrestamos.setBorderPainted(false);
        btnReportePrestamos.setPreferredSize(new Dimension(200, 36));
        cardPrestamos.add(btnReportePrestamos, BorderLayout.SOUTH);

        // CARD REPORTE 2 — INVENTARIO DE EQUIPOS ============================
        JPanel cardEquipos = new JPanel();
        cardEquipos.setLayout(new BorderLayout(0, 10));
        cardEquipos.setBackground(Color.WHITE);
        cardEquipos.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(210, 215, 220)),
                BorderFactory.createEmptyBorder(16, 16, 16, 16)));
        panelPrincipal.add(cardEquipos);

        JLabel lblTituloEquipos = new JLabel("Reporte de Inventario de Equipos");
        lblTituloEquipos.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14));
        lblTituloEquipos.setForeground(new Color(20, 20, 20));
        cardEquipos.add(lblTituloEquipos, BorderLayout.NORTH);

        JLabel lblDescEquipos = new JLabel("<html>Genera un reporte en PDF con todos los equipos registrados,<br>incluyendo código, nombre, tipo, marca, modelo y estado.</html>");
        lblDescEquipos.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 12));
        lblDescEquipos.setForeground(new Color(90, 90, 90));
        cardEquipos.add(lblDescEquipos, BorderLayout.CENTER);

        JButton btnReporteEquipos = new JButton("Generar Reporte PDF");
        btnReporteEquipos.setBackground(new Color(25, 118, 210));
        btnReporteEquipos.setForeground(Color.WHITE);
        btnReporteEquipos.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12));
        btnReporteEquipos.setFocusPainted(false);
        btnReporteEquipos.setBorderPainted(false);
        btnReporteEquipos.setPreferredSize(new Dimension(200, 36));
        cardEquipos.add(btnReporteEquipos, BorderLayout.SOUTH);

        // EVENTOS ==========================================================
        btnReportePrestamos.addActionListener(e -> generarReportePrestamos());
        btnReporteEquipos.addActionListener(e -> generarReporteEquipos());
    }

    // GENERAR REPORTE PRÉSTAMOS ACTIVOS =====================================
    private void generarReportePrestamos() {
        try {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Guardar Reporte de Préstamos");
            fileChooser.setSelectedFile(new java.io.File("Reporte_Prestamos_Activos.pdf"));
            int resultado = fileChooser.showSaveDialog(this);
            if (resultado != JFileChooser.APPROVE_OPTION) return;

            String ruta = fileChooser.getSelectedFile().getAbsolutePath();
            if (!ruta.endsWith(".pdf")) ruta += ".pdf";

            Document doc = new Document(PageSize.A4.rotate());
            PdfWriter.getInstance(doc, new FileOutputStream(ruta));
            doc.open();

            com.lowagie.text.Font fuenteTitulo = new com.lowagie.text.Font(
                com.lowagie.text.Font.HELVETICA, 16, com.lowagie.text.Font.BOLD, new Color(25, 118, 210));
            com.lowagie.text.Font fuenteSubtitulo = new com.lowagie.text.Font(
                com.lowagie.text.Font.HELVETICA, 10, com.lowagie.text.Font.NORMAL, new Color(90, 90, 90));
            com.lowagie.text.Font fuenteEncabezado = new com.lowagie.text.Font(
                com.lowagie.text.Font.HELVETICA, 10, com.lowagie.text.Font.BOLD, Color.WHITE);
            com.lowagie.text.Font fuenteDato = new com.lowagie.text.Font(
                com.lowagie.text.Font.HELVETICA, 9, com.lowagie.text.Font.NORMAL, Color.BLACK);

            Paragraph titulo = new Paragraph("REPORTE DE PRÉSTAMOS ACTIVOS\n", fuenteTitulo);
            titulo.setAlignment(Element.ALIGN_CENTER);
            doc.add(titulo);

            Paragraph subtitulo = new Paragraph(
                "I.E. Virgo Potens — Generado el: " +
                LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "\n\n",
                fuenteSubtitulo);
            subtitulo.setAlignment(Element.ALIGN_CENTER);
            doc.add(subtitulo);

            PdfPTable tabla = new PdfPTable(6);
            tabla.setWidthPercentage(100);
            tabla.setWidths(new float[]{0.8f, 2.5f, 2.5f, 1.8f, 1.8f, 1.5f});

            String[] encabezados = {"ID", "Solicitante", "Equipo", "F. Préstamo", "F. Dev. Prev.", "Estado"};
            for (String enc : encabezados) {
                PdfPCell cell = new PdfPCell(new Phrase(enc, fuenteEncabezado));
                cell.setBackgroundColor(new Color(25, 118, 210));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setPadding(6);
                tabla.addCell(cell);
            }

            PrestamoDao prestamoDAO = new PrestamoDao();
            EquipoDAO equipoDAO = new EquipoDAO();
            SolicitanteDao solicitanteDAO = new SolicitanteDao();
            ArrayList<Equipo> equipos = equipoDAO.listar();
            ArrayList<Solicitante> solicitantes = solicitanteDAO.listar();
            ArrayList<Prestamo> prestamos = prestamoDAO.listar();

            boolean filaPar = false;
            for (Prestamo p : prestamos) {
                if (!p.getEstado().equals("Activo")) continue;

                String nombreSolicitante = String.valueOf(p.getIdSolicitante());
                for (Solicitante s : solicitantes) {
                    if (s.getIdSolicitante() == p.getIdSolicitante()) {
                        nombreSolicitante = s.getNombre() + " " + s.getApellidos();
                        break;
                    }
                }

                String codigoEquipo = String.valueOf(p.getIdEquipo());
                for (Equipo eq : equipos) {
                    if (eq.getIdEquipo() == p.getIdEquipo()) {
                        codigoEquipo = eq.getCodigo() + " - " + eq.getNombre();
                        break;
                    }
                }

                Color colorFila = filaPar ? new Color(245, 247, 250) : Color.WHITE;
                filaPar = !filaPar;

                String[] datos = {
                    String.valueOf(p.getIdPrestamo()),
                    nombreSolicitante,
                    codigoEquipo,
                    p.getFechaPrestamo().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                    p.getFechaDevolucionPrevista().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                    p.getEstado()
                };

                for (String dato : datos) {
                    PdfPCell cell = new PdfPCell(new Phrase(dato, fuenteDato));
                    cell.setBackgroundColor(colorFila);
                    cell.setPadding(5);
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    tabla.addCell(cell);
                }
            }

            doc.add(tabla);
            doc.close();

            JOptionPane.showMessageDialog(this,
                "Reporte generado correctamente en:\n" + ruta,
                "Éxito", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                "Error al generar el reporte: " + ex.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // GENERAR REPORTE INVENTARIO DE EQUIPOS =================================
    private void generarReporteEquipos() {
        try {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Guardar Reporte de Equipos");
            fileChooser.setSelectedFile(new java.io.File("Reporte_Inventario_Equipos.pdf"));
            int resultado = fileChooser.showSaveDialog(this);
            if (resultado != JFileChooser.APPROVE_OPTION) return;

            String ruta = fileChooser.getSelectedFile().getAbsolutePath();
            if (!ruta.endsWith(".pdf")) ruta += ".pdf";

            Document doc = new Document(PageSize.A4.rotate());
            PdfWriter.getInstance(doc, new FileOutputStream(ruta));
            doc.open();

            com.lowagie.text.Font fuenteTitulo = new com.lowagie.text.Font(
                com.lowagie.text.Font.HELVETICA, 16, com.lowagie.text.Font.BOLD, new Color(25, 118, 210));
            com.lowagie.text.Font fuenteSubtitulo = new com.lowagie.text.Font(
                com.lowagie.text.Font.HELVETICA, 10, com.lowagie.text.Font.NORMAL, new Color(90, 90, 90));
            com.lowagie.text.Font fuenteEncabezado = new com.lowagie.text.Font(
                com.lowagie.text.Font.HELVETICA, 10, com.lowagie.text.Font.BOLD, Color.WHITE);
            com.lowagie.text.Font fuenteDato = new com.lowagie.text.Font(
                com.lowagie.text.Font.HELVETICA, 9, com.lowagie.text.Font.NORMAL, Color.BLACK);

            Paragraph titulo = new Paragraph("REPORTE DE INVENTARIO DE EQUIPOS\n", fuenteTitulo);
            titulo.setAlignment(Element.ALIGN_CENTER);
            doc.add(titulo);

            Paragraph subtitulo = new Paragraph(
                "I.E. Virgo Potens — Generado el: " +
                LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "\n\n",
                fuenteSubtitulo);
            subtitulo.setAlignment(Element.ALIGN_CENTER);
            doc.add(subtitulo);

            PdfPTable tabla = new PdfPTable(7);
            tabla.setWidthPercentage(100);
            tabla.setWidths(new float[]{0.8f, 1.5f, 2f, 1.5f, 1.5f, 1.5f, 1.5f});

            String[] encabezados = {"ID", "Código", "Nombre", "Tipo", "Marca", "Modelo", "Estado"};
            for (String enc : encabezados) {
                PdfPCell cell = new PdfPCell(new Phrase(enc, fuenteEncabezado));
                cell.setBackgroundColor(new Color(25, 118, 210));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setPadding(6);
                tabla.addCell(cell);
            }

            EquipoDAO equipoDAO = new EquipoDAO();
            ArrayList<Equipo> equipos = equipoDAO.listar();

            boolean filaPar = false;
            for (Equipo eq : equipos) {
                Color colorFila = filaPar ? new Color(245, 247, 250) : Color.WHITE;
                filaPar = !filaPar;

                String[] datos = {
                    String.valueOf(eq.getIdEquipo()),
                    eq.getCodigo(),
                    eq.getNombre(),
                    eq.getTipo(),
                    eq.getMarca(),
                    eq.getModelo() != null ? eq.getModelo() : "",
                    eq.getEstado()
                };

                for (String dato : datos) {
                    PdfPCell cell = new PdfPCell(new Phrase(dato, fuenteDato));
                    cell.setBackgroundColor(colorFila);
                    cell.setPadding(5);
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    tabla.addCell(cell);
                }
            }

            doc.add(tabla);
            doc.close();

            JOptionPane.showMessageDialog(this,
                "Reporte generado correctamente en:\n" + ruta,
                "Éxito", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                "Error al generar el reporte: " + ex.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}