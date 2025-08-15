<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="container my-5">
    <h1 class="mb-4">Contact</h1>

    <div class="row g-4">
        <div class="col-md-6">
            <h2 class="h5">Parlez-nous de votre projet</h2>
            <p class="text-muted">
                Une question sur un produit, un devis d’impression 3D, ou une idée à prototyper&nbsp;?
                Écrivez-nous, on répond vite&nbsp;!
            </p>

            <!-- Formulaire non connecté (placeholder) -->

                <div class="mb-3">
                    <label for="name" class="form-label">Nom</label>
                    <input id="name" name="name" type="text" class="form-control" required/>
                </div>

                <div class="mb-3">
                    <label for="email" class="form-label">Email</label>
                    <input id="email" name="email" type="email" class="form-control" required/>
                </div>

                <div class="mb-3">
                    <label for="subject" class="form-label">Sujet</label>
                    <input id="subject" name="subject" type="text" class="form-control"/>
                </div>

                <div class="mb-3">
                    <label for="message" class="form-label">Message</label>
                    <textarea id="message" name="message" rows="5" class="form-control" required></textarea>
                </div>

                <button onclick="alert('Fonctionnalité pas encore disponible');" class="btn btn-dark">Envoyer</button>

        </div>

        <div class="col-md-6">
            <div class="p-4 bg-light border rounded-3 h-100">
                <h2 class="h5 mb-3">Coordonnées</h2>
                <ul class="list-unstyled mb-4">
                    <li><strong>Email&nbsp;:</strong> support@printable.green</li>
                    <li><strong>Téléphone&nbsp;:</strong> +33 1 86 95 00 00</li>
                    <li><strong>Adresse&nbsp;:</strong> 12 rue du Recyclage, 75011 Paris, France</li>
                </ul>

                <h2 class="h5 mb-3">Horaires</h2>
                <p class="mb-0">Lun–Ven&nbsp;: 9h–18h<br/>Sam&nbsp;: 10h–14h</p>
            </div>
        </div>
    </div>
</div>
